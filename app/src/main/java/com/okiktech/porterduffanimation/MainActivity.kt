package com.okiktech.porterduffanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PorterDuffAnimationTheme {
        GhostTextAnimation()
    }
}