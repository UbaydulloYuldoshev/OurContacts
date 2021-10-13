package uz.gita.ourcontacts.data

import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.ourcontacts.app.App

object ApiClient {

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://ecf2-89-146-126-125.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(App.instance))
            .build())
        .build()
}