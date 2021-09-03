package developer.jkey20.composehilt

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts/list")
    suspend fun getNoticeList() : List<Notice>

}