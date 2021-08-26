package developer.jkey20.composehilt

import retrofit2.Response

class UseCase(private val repository: Repository) {
    suspend fun getInfo() : List<Notice> {
        return repository.getInfo()
    }
}