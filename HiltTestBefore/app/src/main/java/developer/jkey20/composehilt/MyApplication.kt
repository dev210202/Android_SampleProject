package developer.jkey20.composehilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// todo 4. application class 생성, @HiltAndroidApp 붙임
// todo 5. manifest에서 application에 name 설정
@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}