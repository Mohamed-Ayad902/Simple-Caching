package com.example.simplecaching.di

import android.content.Context
import androidx.room.Room
import com.example.simplecaching.data.repository.PostRepositoryImpl
import com.example.simplecaching.data.source.local.PostDao
import com.example.simplecaching.data.source.local.PostDatabase
import com.example.simplecaching.data.source.remote.PostApi
import com.example.simplecaching.domain.mapper.DtoToEntity
import com.example.simplecaching.domain.mapper.EntityToDomain
import com.example.simplecaching.domain.repository.IPostRepository
import com.example.simplecaching.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEntityToDomain() = EntityToDomain()

    @Singleton
    @Provides
    fun provideDtoEntity() = DtoToEntity()

    @Singleton
    @Provides
    fun provideRepository(
        postMapper: EntityToDomain,
        entityMapper: DtoToEntity,
        api: PostApi,
        dao: PostDao
    ): IPostRepository = PostRepositoryImpl(
        postMapper,
        entityMapper,
        dao,
        api
    )

    //---------------------------- retrofit --------------------------
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    //---------------------------- room --------------------------
    @Singleton
    @Provides
    fun providePostDao(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java, "post_db")
            .fallbackToDestructiveMigration()
            .build()
            .dao()

}