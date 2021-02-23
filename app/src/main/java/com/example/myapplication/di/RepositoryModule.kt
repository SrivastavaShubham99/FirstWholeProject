package com.example.myapplication.di

import com.example.myapplication.repository.UserRespository
import org.koin.dsl.module

var RepositoryModule= module {
    single { UserRespository() }
}