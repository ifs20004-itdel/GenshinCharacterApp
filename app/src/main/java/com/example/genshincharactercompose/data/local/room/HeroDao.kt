package com.example.genshincharactercompose.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genshincharactercompose.data.local.entity.DetailHeroEntity
import com.example.genshincharactercompose.data.local.entity.HeroEntity

@Dao
interface HeroDao {
    @Query("SELECT * FROM genshin_hero")
    fun getHero():LiveData<List<HeroEntity>>

    @Query("SELECT * FROM description WHERE name=:name")
    fun getHeroDetail(name: String):LiveData<DetailHeroEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveHero(hero: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveHeroDetail(detailHero: DetailHeroEntity)

    @Query("DELETE FROM genshin_hero WHERE name = :heroName")
    suspend fun deleteHero(heroName: String)

    @Query("DELETE FROM description WHERE name = :detailHero")
    suspend fun deleteDetailHero(detailHero: String)

    @Query("SELECT EXISTS(SELECT * FROM genshin_hero WHERE name = :heroName)")
    fun isFavoriteHero(heroName: String): LiveData<Boolean>


}