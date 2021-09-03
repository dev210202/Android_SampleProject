package developer.jkey20.composehilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import developer.jkey20.composehilt.ui.theme.ComposeHiltTheme


// todo 6. @AndroidEntryPoint 주석 지정
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainVm : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeHiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Layout()
                }
            }
        }
    }


    @Composable
    fun Layout() {
        Text("HI")
         mainVm.getInfo()
    }
}