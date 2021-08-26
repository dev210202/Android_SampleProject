package developer.jkey20.composehilt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainViewModel : ViewModel() {
    val loggingInterceptor = HttpLoggingInterceptor()

    val retrofit = Retrofit.Builder()
    val request = buildRetrofit(retrofit, ApiService::class.java)

    private val dataSource = APIRemoteSourceImpl(request)
    private val repositoryImpl = RepositoryImpl(dataSource)
    private val useCase = UseCase(repositoryImpl)

    var list = listOf<Notice>()
    fun getInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            list = useCase.getInfo()

            Log.i("LIST", list.get(0).title)
        }
    }

    private fun getInterceptor(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.MINUTES)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun <T> buildRetrofit(retrofit: Retrofit.Builder, service: Class<T>) =
        retrofit.client(getInterceptor(loggingInterceptor))
            .baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
}