package developer.jkey20.composehilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Provides
    @Singleton
    fun provideRemoteSource(api : ApiService) : APIRemoteSource = APIRemoteSourceImpl(api)
}