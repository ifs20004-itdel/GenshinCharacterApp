package com.example.genshincharactercompose.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.genshincharactercompose.data.local.entity.DetailHeroEntity
import com.example.genshincharactercompose.data.local.entity.HeroEntity

@Database(
    entities =
    [  HeroEntity::class,
        DetailHeroEntity::class]
    , version = 1,
    exportSchema = false)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao

    companion object{
        @Volatile
        private var instance: HeroDatabase? = null
        fun getInstance(context: Context): HeroDatabase=
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    HeroDatabase::class.java, "Genshin.db"
                ).build()
            }
    }
}