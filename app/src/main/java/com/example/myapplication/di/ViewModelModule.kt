package com.example.myapplication.di

import com.example.myapplication.viewmodel.MyViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var ViewModelModule= module {
    viewModel { MyViewmodel(get ()) }
}