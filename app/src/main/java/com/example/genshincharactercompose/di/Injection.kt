package com.example.genshincharactercompose.di

import android.content.Context
import com.example.genshincharactercompose.data.HeroRepository
import com.example.genshincharactercompose.data.local.room.HeroDatabase

object Injection {
    fun provideRepository(context: Context): HeroRepository{
        val database = HeroDatabase.getInstance(context)
        val dao = database.heroDao()
        return HeroRepository.getInstance(dao)
    }
}