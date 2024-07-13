package com.okiktech.porterduffanimation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okiktech.porterduffanimation.ui.theme.PorterDuffAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PorterDuffAnimationTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    GhostTextAnimation()
                }
            }
        }
    }
}

@Composable
fun GhostTextAnimation() {
    val context = LocalContext.current
    val density = LocalDensity.current

    val textWidth = with(density) { 200.dp.toPx() }
    val animatedwidth = with(density) { 170.dp.toPx() }

    val infiniteTransition = rememberInfiniteTransition(label = "text infinite animation")

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = animatedwidth,
        targetValue = animatedwidth,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated offset"
    )

    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated scale"
    )

    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated color"
    )

    val rotatedAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated angle"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val doggyBitmap = remember {
            BitmapFactory.decodeResource(context.resources, R.drawable.doggy)
        }

        val resizedDoggyBitmap = Bitmap.createScaledBitmap(
            /* src = */ doggyBitmap,
            /* dstWidth = */ (textWidth / 2).toInt(),
            /* dstHeight = */ (textWidth / 2).toInt(),
            /* filter = */ true
        )

        Spacer(
            modifier = Modifier
                .size(size = 400.dp)
                .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale)
                .drawWithContent {
                    drawContent()

                    val textBitmap = Bitmap.createBitmap(
                        size.width.toInt(),
                        size.height.toInt(),
                        Bitmap.Config.ARGB_8888
                    )
                    val textCanvas = android.graphics.Canvas(textBitmap)

                    val textPaint = android.graphics.Paint().apply {
                        isAntiAlias = true
                        textSize = 48.sp.toPx()
                        color = animatedColor.toArgb()
                        textAlign = android.graphics.Paint.Align.CENTER
                        typeface = android.graphics.Typeface.create("", android.graphics.Typeface.BOLD)
                    }

                    val textX = size.width / 2
                    val textY = size.height / 2 - (textPaint.descent() - textPaint.ascent()) / 2
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PorterDuffAnimationTheme {
        GhostTextAnimation()
    }
}