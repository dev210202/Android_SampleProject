package developer.jkey20.sockettest

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URISyntaxException

class MainViewModel : ViewModel() {

    var messageList = mutableStateListOf<Message>()
    lateinit var socket : Socket
    var text = mutableStateOf("")
    var namespace = "/chat"
    var url = "https://chatting-demo.herokuapp.com"
    var pushEvent = "chat-push"
    var getEvent = "chat-get"
    var message = ""
    fun runSocket() {
        try {
            socket = IO.socket(url + namespace)
        } catch (exception: URISyntaxException) {
            Log.e("Socket Exception", exception.message!!)
        }
        socket.connect()

        socket.on(io.socket.client.Socket.EVENT_CONNECT){

            Log.i(" CONNECT", "SOCKET")
        }.on(io.socket.client.Socket.EVENT_DISCONNECT){

            Log.i(" DISCONNECT", "SOCKET")
        }.on(Socket.EVENT_CONNECT_ERROR){

            Log.i("ERROR CONNECT", "SOCKET")
        }

        socket.on(getEvent, getReceiver())

    }
    fun getReceiver(): Emitter.Listener {

        val messageReceiver = Emitter.Listener {
            messageList.add(Message(it[0].toString(), "other"))
        }
        return messageReceiver
    }
    fun inputSocket(){

        val data = JSONObject()

        try{
            data.put("mirrorMode", false)
            data.put("user", "android")
            data.put("msg", text.value)
            socket.emit(pushEvent, data)
            Log.i("SUCCESS INPUT", "SOCKET")
            Log.i("DATA INFO", data.toString())

            messageList.add(Message(text.value, "me"))

        }catch (exception : Exception){
            Log.e("data Exception", exception.message!!)
        }

    }

}