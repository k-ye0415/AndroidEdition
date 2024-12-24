# OkHttp Interceptor

## Interceptor 란
Interceptor 의 사전적 의미는 가로채는 사람[것], 가로막는 사람[것] 으로 의미 되지만 OkHttp 에서는 네트워크 호출을 모니터링하거나, 고쳐쓰거나 재시도할 수 있는 강력한 매커니즘이다.
```java
class LoggingInterceptor implements Interceptor {
  @Override public Response intercept(Interceptor.Chain chain) throws IOException {
    // 서버로 보내는 request
    Request request = chain.request();

    long t1 = System.nanoTime();
    logger.info(String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers()));

    // 서버로부터 받은 response
    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    logger.info(String.format("Received response for %s in %.1fms%n%s",
        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

    return response;
  }
}
```
위의 코드에서 `chain.preceed` 부분이 Interceptor 매커니즘에서 가장 핵심적인 부분이다.  
간단해 보이는 메소드이지만 실상은 모든 HTTP 작업이 이뤄지며, `request` 를 만족하는 `response` 를 생성해낸다.  
> ✏️ `chain.preceed` 메소드가 2번 이상 호출되는 경우 이전 response body 를 닫아야 한다.

여러개의 Interceptor 들은 연결될 수 있다. Interceptor 가 2개 이상일 경우 어떤 Interceptor 가 먼저 동작되어야 하는지 결정해야 하는데, OkHttp 는 Interceptor 를 추적하기 위해 리스트를 사용하며, Interceptor 는 정의된 순서대로 호출된다.
![interceptor_1](../Android_image/interceptors_1.png)

Interceptor 는 2가지 유형으로 나눌 수 있다.
- [Application Interceptors](#application-interceptors) : 네트워크 요청 및 응답을 가로채어 애플리케이션 수준에서 작업을 수행한다.  
✏️ 공통 헤더를 추가하거나 로깅하는 것이 포함된다.
- [Network Interceptors](#network-interceptors) : 네트워크 요청 및 응답을 가로채어 네트워크 수준에서 작업을 수행한다.  
✏️ 캐싱, 요청 재시도, 인증 토큰 갱신 등이 포함된다.  

앞서 작성된 `LoggingInterceptor` 코드를 통해 각각의 Interceptor 의 예시이다.

### Application Interceptors
OkHttpClinet.Builder 에 addInterceptor() 를 통해 Application Interceptor 를 등록한다.
```java
OkHttpClient client = new OkHttpClient.Builder()
    .addInterceptor(new LoggingInterceptor())
    .build();

Request request = new Request.Builder()
    .url("http://www.publicobject.com/helloworld.txt")
    .header("User-Agent", "OkHttp Example")
    .build();

Response response = client.newCall(request).execute();
response.body().close();
```
<p>
해당 코드를 보면, <code>http://www.publicobject.com/helloworld.txt</code> URL 은 <code>https://publicobject.com/helloworld.txt</code> 로 <span class="tooltip-container">
  <strong>리디렉션</strong>
  <span class="tooltip">
    1.…을 다시 향하다, 새 방향으로 돌리다; [영] <편지의> 수신인 이름[주소]을 고치다(forward)<br>
2.…의 방향[초점]을 바꾸다
  </span>
</span> 되며 OkHttp 는 이를 자동으로 따라간다.  
이 때 Application Interceptor 는 한 번 호출되며, <code>chain.proceed()</code> 에서 반환된 응답은 리디렉션된 응답을 포함한다.
</p>

```text
INFO: Sending request http://www.publicobject.com/helloworld.txt on null
User-Agent: OkHttp Example

INFO: Received response for https://publicobject.com/helloworld.txt in 1179.7ms
Server: nginx/1.4.6 (Ubuntu)
Content-Type: text/plain
Content-Length: 1759
Connection: keep-alive
```
로그로 확인 해 보면, `response.request().url()` 과 `request.url()` 이 서로 다르기 때문에 리디렉션이 발생했음을 확인할 수 있다. 또한, 두 개의 서로 다른 URL 이 기록된 것도 확인할 수 있다.


### Network Interceptors
addInterceptor() 대신 addNetworkInterceptor 를 사용.
```java
OkHttpClient client = new OkHttpClient.Builder()
    .addNetworkInterceptor(new LoggingInterceptor())
    .build();

Request request = new Request.Builder()
    .url("http://www.publicobject.com/helloworld.txt")
    .header("User-Agent", "OkHttp Example")
    .build();

Response response = client.newCall(request).execute();
response.body().close();
```
해당 코드를 실행시키면 Interceptor 는 두 번 실행된다. `http://www.publicobject.com/helloworld.txt` 에 초기 요처을 위해 한 번, `https://publicobject.com/helloworld.txt` 로 리디렉션하기 위해 또 다시 한 번 실행된다.

```text
// 초기 요청(`http://www.publicobject.com/helloworld.txt`)에 대한 요청 및 응답
INFO: Sending request http://www.publicobject.com/helloworld.txt on Connection{www.publicobject.com:80, proxy=DIRECT hostAddress=54.187.32.157 cipherSuite=none protocol=http/1.1}
User-Agent: OkHttp Example
Host: www.publicobject.com
Connection: Keep-Alive
Accept-Encoding: gzip

INFO: Received response for http://www.publicobject.com/helloworld.txt in 115.6ms
Server: nginx/1.4.6 (Ubuntu)
Content-Type: text/html
Content-Length: 193
Connection: keep-alive
Location: https://publicobject.com/helloworld.txt

// 리디렉션된 요청(`https://publicobject.com/helloworld.txt`)에 대한 요청 및 응답
INFO: Sending request https://publicobject.com/helloworld.txt on Connection{publicobject.com:443, proxy=DIRECT hostAddress=54.187.32.157 cipherSuite=TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA protocol=http/1.1}
User-Agent: OkHttp Example
Host: publicobject.com
Connection: Keep-Alive
Accept-Encoding: gzip

INFO: Received response for https://publicobject.com/helloworld.txt in 80.9ms
Server: nginx/1.4.6 (Ubuntu)
Content-Type: text/plain
Content-Length: 1759
Connection: keep-alive
```
네트워크 요청에는 OkHttp 가 추가한 `Accept-Encoding: gzip` 헤더와 같은 추가 데이터도 포함된다. 이 헤더는 서버가 응답 압축을 지원함을 알리기 위해 사용된다.  
또한, Network Interceptor 의 `Chain` 객체에는 null 이 아닌 `Connection`이 포함되어 있어, 해당 웹 서버에 연결할 때 사용된 IP 주소 및 TLS 설정을 확인할 수 있다.


### Application vs Network
- Application Interceptors
    - 리다이렉트나 재시도와 같은 중간 응답에 대해 신경 쓸 필요가 없다.
    - HTTP 응답이 캐시에서 제공되더라도 항상 한 번 호출된다.
    - 애플리케이션의 원래 의도를 관찰하며, `If-None-Match` 와 같은 OkHttp 에서 추가된 헤더에는 관여하지 않는다.
    - `Chain.proceed()` 메서드를 호출하지 않고 바로 반환할 수 있다.
    - `Chain.proceed()` 메서드를 재시도하고 여러 번 호출될 수 있다.
    - `withConnectTimeout`, `withReadTimeout`, `withWriteTimeout` 을 사용하여 호출 시간 초과를 조정할 수 있다.
- Network Interceptors
    - 리다이렉트나 재시도와 같은 중간 응답에 대해 작동 할 수 있다.
    - 네트워크를 우회하여 캐시된 응답에 대해서는 호출되지 않는다.
    - 데이터가 네트워크를 통해 전송되는 그대로의 데이터를 관찰한다.
    - 요청을 전달하는 `Connection` 에 접근할 수 있다.

## Intercepteor 가 수행할 수 있는 다양한 작업
### Rewriting Requests
```java
/** This interceptor compresses the HTTP request body. Many webservers can't handle this! */
final class GzipRequestInterceptor implements Interceptor {
  @Override public Response intercept(Interceptor.Chain chain) throws IOException {
    Request originalRequest = chain.request();
    if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
      return chain.proceed(originalRequest);
    }

    Request compressedRequest = originalRequest.newBuilder()
        .header("Content-Encoding", "gzip")
        .method(originalRequest.method(), gzip(originalRequest.body()))
        .build();
    return chain.proceed(compressedRequest);
  }

  private RequestBody gzip(final RequestBody body) {
    return new RequestBody() {
      @Override public MediaType contentType() {
        return body.contentType();
      }

      @Override public long contentLength() {
        return -1; // We don't know the compressed length in advance!
      }

      @Override public void writeTo(BufferedSink sink) throws IOException {
        BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
        body.writeTo(gzipSink);
        gzipSink.close();
      }
    };
  }
}
```
Interceptor 는 request header 를 추가, 제거, 교체를 할 수 있다. 또한, request header 가 있는 경우 request 의 body 부분을 변환할 수 있다.   
✏️ 연결하려는 웹 서버가 요청 본문 압축 기능을 지원하는 경우 Application Interceptor 를 사용하여 request 본문을 압축할 수 있다.

### Rewriting Responses
```java
/** Dangerous interceptor that rewrites the server's cache-control header. */
private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
  @Override public Response intercept(Interceptor.Chain chain) throws IOException {
    Response originalResponse = chain.proceed(chain.request());
    return originalResponse.newBuilder()
        .header("Cache-Control", "max-age=60")
        .build();
  }
};
```
Request 와 대칭적으로 Interceptor 는 response header 를 다시 작성하거나 response body 를 변환할 수 있다. 하지만 이는 일반적으로 request header 를 다시 작성하는 것 보다 더 위험할 수 있는데, 그 이유는 웹 서버의 기대에 어긋날 수 있기 때문이다.

### 공통 헤더 추가
```kotlin
class CommonHeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        requestBuilder.header("User-Agent", "MyApp/1.0")
        requestBuilder.header("Content-Type", "application/json")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
```
모든 요청에 공통 헤더를 추가하는 interceptor 를 구현하여 사용할 수 있다.

### 캐싱
```kotlin
val cacheSize = 10 * 1024 * 1024L // 10MB
val cache = Cache(context.cacheDir, cacheSize)

val client = OkHttpClient.Builder()
    .cache(cache)
    .addNetworkInterceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxSaveTime = 60 // 캐시 유지 시간: 60초
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxSaveTime")
            .build()
    }
    .addInterceptor { chain ->
        var request = chain.request()
        if (!isNetworkAvailable(context)) { // 네트워크 연결 확인
            val holdingTime = 60 * 60 * 24 * 7 // 오프라인 상태에서 캐시 유지 시간: 1주
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$holdingTime")
                .build()
        }
        chain.proceed(request)
    }
    .build()
```
Network Interceptor 를 사용하여 response 에 캐시 제어를 추가하고, Application Interceptor 를 사용하여 오프라인 상태에서 캐시된 데이터를 사용하도록 설정할 수 있다.

[참고 자료](https://square.github.io/okhttp/features/interceptors/)  
[참고 자료](https://velog.io/@ows3090/Android-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-OkHttp-Interceptor-%ED%99%9C%EC%9A%A9)  
[참고 자료](https://jgeun97.tistory.com/371)  
[참고 자료](https://velog.io/@heetaeheo/OkHttp-Interceptors)


<style>
.tooltip {
  visibility: hidden;
  width: 200px;
  background-color: lightyellow;
  color: black;
  text-align: start;
  border-radius: 5px;
  padding: 5px;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  transform: translateX(-50%);
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}
.tooltip-container {
  position: relative;
  cursor: pointer;
}
.tooltip-container:hover .tooltip {
  visibility: visible;
}
.tooltip-container strong {
  color:rgb(18, 183, 51);
}
</style>