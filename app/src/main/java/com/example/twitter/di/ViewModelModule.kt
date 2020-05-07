package com.example.twitter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter.ui.viewModel.CreateTweetViewModel
import com.example.twitter.ui.viewModel.HomeTweetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CreateTweetViewModel::class)
    internal abstract fun createViewModel(viewModel: CreateTweetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeTweetViewModel::class)
    internal abstract fun timelineViewModel(viewModel: HomeTweetViewModel): ViewModel
}