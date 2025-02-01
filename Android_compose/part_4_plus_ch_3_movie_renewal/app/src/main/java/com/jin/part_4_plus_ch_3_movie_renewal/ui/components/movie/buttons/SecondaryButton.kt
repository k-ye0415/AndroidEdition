package com.jin.part_4_plus_ch_3_movie_renewal.ui.components.movie.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.Paddings
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.Part_4_plus_ch_3_movie_renewalTheme
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.dialogButton
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.myColors

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        border = BorderStroke(2.dp, MaterialTheme.myColors.secondary),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.myColors.background,
            contentColor = MaterialTheme.myColors.secondary,
            disabledContentColor = MaterialTheme.myColors.background,
            disabledContainerColor = MaterialTheme.myColors.disabledSecondary
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id) } ?: text,
                style = MaterialTheme.typography.dialogButton,
                modifier = Modifier.padding(Paddings.small),
            )
        }
    }
}

@Preview
@Composable
fun SecondaryButtonPreview() {
    Part_4_plus_ch_3_movie_renewalTheme {
        SecondaryButton(text = "SUBMIT") {

        }
    }
}