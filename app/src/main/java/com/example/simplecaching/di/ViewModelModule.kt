package com.example.simplecaching.di

import com.example.simplecaching.domain.repository.IPostRepository
import com.example.simplecaching.domain.use_cases.home.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    //---------------------------- use cases --------------------------
    @ViewModelScoped
    @Provides
    fun provideGetPostUseCase(repository: IPostRepository) = GetPostsUseCase(repository)

}