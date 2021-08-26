package developer.jkey20.composehilt

import retrofit2.Response
import javax.inject.Inject

interface APIRemoteSource {
    suspend fun getInfo() : List<Notice>
}


class APIRemoteSourceImpl(private val api : ApiService) : APIRemoteSource{
    override suspend fun getInfo(): List<Notice> {
        return api.getNoticeList()
    }

}