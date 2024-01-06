package com.govahan.com.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.govahan.com.data.ApiService
import com.govahan.com.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttp() : OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout((60 * 5).toLong(), TimeUnit.SECONDS)
            .readTimeout((60 * 5).toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        builder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.method(original.method, original.body)

            val request = requestBuilder.build()

            chain.proceed(request)
        }
        return builder.build()
    }


    @Singleton
    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    var gson: Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
//            .baseUrl("http://172.16.0.238/~apitest/transport/public/api/")
//        "http://182.76.237.238/~apitest/transport/public/api/"
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
    //test http://172.16.0.238/~apitest/transport/public/api/user_login
    //live //http://182.76.237.238/~apitest/transport/public/api/
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}