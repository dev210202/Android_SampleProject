package developer.jkey20.composehilt

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: APIRemoteSource
) : Repository {
    override suspend fun getInfo(): List<Notice> {
        return dataSource.getInfo()
    }

}