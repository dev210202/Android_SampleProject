package developer.jkey20.sockettest

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import developer.jkey20.sockettest.ui.theme.ChatColor
import developer.jkey20.sockettest.ui.theme.SocketTestTheme
import io.socket.client.Socket

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.runSocket()
        setContent {
            SocketTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content()
                }
            }
        }
    }

    @Composable
    fun Content() {
        Column() {
            LazyColumn() {
                items(items = viewModel.messageList) { message ->
                    Log.i("Text", message.text)
                    Log.i("SENDER", message.sender)
                    if (message.sender == "other" ) {
                        OtherBubble(message.text)
                        Log.i("OTHER BUBBLE","!")
                    } else {
                        OwnBubble(message.text)
                        Log.i("OWN BUBBLE","!")
                    }
                }
            }

            TextField(
                value = viewModel.text.value,
                onValueChange = { viewModel.text.value = it })
            Button(onClick = {
                viewModel.inputSocket()
            }) {
                Text("send message")
            }
        }
    }

    @Composable
    private fun OtherBubble(message: String) { // 상대 채팅 버블
        val defaultHeight = 50.dp
        val shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomEnd = 15.dp)

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (profileImage, bubble) = createRefs()

            Spacer(
                modifier = Modifier
                    .size(defaultHeight)
                    .background(ChatColor.ProfileImageBackgroundColor, CircleShape)
                    .clip(CircleShape)
                    .constrainAs(profileImage) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                modifier = Modifier
                    .heightIn(min = defaultHeight)
                    .clip(shape)
                    .background(color = ChatColor.OtherBubbleBackgroundColor, shape = shape)
                    .constrainAs(bubble) {
                        start.linkTo(profileImage.end, 10.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(15.dp),
                text = message,
                color = Color.Black
            )
        }
    }

    @Composable
    private fun OwnBubble(message: String) { // 내 채팅 버블
        val defaultHeight = 50.dp
        val shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp)

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (bubble, profileImage) = createRefs()

            Text(
                modifier = Modifier
                    .heightIn(min = defaultHeight)
                    .clip(shape)
                    .background(color = ChatColor.OwnBubbleBackgroundColor, shape = shape)
                    .constrainAs(bubble) {
                        start.linkTo(parent.start)
                        end.linkTo(profileImage.start, 10.dp)
                        width = Dimension.fillToConstraints
                    }
                    .padding(15.dp),
                text = message,
                color = Color.White
            )
            Spacer(
                modifier = Modifier
                    .size(defaultHeight)
                    .background(ChatColor.ProfileImageBackgroundColor, CircleShape)
                    .clip(CircleShape)
                    .constrainAs(profileImage) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}
