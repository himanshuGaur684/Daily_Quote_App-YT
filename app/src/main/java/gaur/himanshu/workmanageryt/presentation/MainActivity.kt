package gaur.himanshu.workmanageryt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gaur.himanshu.workmanageryt.presentation.ui.theme.WorkManagerYTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerYTTheme {
                Surface(modifier = Modifier.safeContentPadding()) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

}

