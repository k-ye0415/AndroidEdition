package volume01;

public class Chatper_16_InputBox {
	Chatepr_16_KeyEventListener listener;

	public void setKeyListener(Chatepr_16_KeyEventListener listener) {
		this.listener = listener;
	}

	public static final int KEY_DOWN = 2;
	public static final int KEY_UP = 4;

	public void listenerCalled(int eventType) {
		if (eventType == KEY_DOWN) {
			listener.onKeyDown();
		} else {
			listener.onKeyUp();
		}
	}
}
