package com.example.core_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class TextDimens(
    val h1: TextUnit,
    val h2: TextUnit,
    val h3: TextUnit,
    val title: TextUnit,
    val headline: TextUnit,
    val body: TextUnit,
    val small: TextUnit,
    val caption: TextUnit
)

@Immutable
data class SpacingDimens(
    val spacing_2xs: Dp,
    val spacing_xs: Dp,
    val spacing_sm: Dp,
    val spacing_md: Dp,
    val spacing_lg: Dp,
    val spacing_xl: Dp,
    val spacing_2xl: Dp,
    val spacing_3xl: Dp,
    val spacing_4xl: Dp,
    val spacing_5xl: Dp,
)

@Immutable
object Dimensions {
    val text: TextDimens
    @Composable
    get() = TextDimens(
        h1 = 34.sp,
        h2 = 32.sp,
        h3 = 22.sp,
        title = 28.sp,
        headline = 17.sp,
        body = 17.sp,
        small = 14.sp,
        caption = 12.sp
    )

    val spacing: SpacingDimens
        @Composable
        get() = SpacingDimens(
            spacing_2xs = 2.dp,
            spacing_xs = 6.dp,
            spacing_sm = 8.dp,
            spacing_md = 16.dp,
            spacing_lg = 24.dp,
            spacing_xl = 32.dp,
            spacing_2xl = 40.dp,
            spacing_3xl = 64.dp,
            spacing_4xl = 72.dp,
            spacing_5xl = 96.dp,
        )
}
