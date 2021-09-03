package developer.jkey20.composehilt

import retrofit2.Response

interface Repository {
    suspend fun getInfo() : List<Notice>
}