package com.example.genshincharactercompose.data
import androidx.lifecycle.LiveData
import com.example.genshincharactercompose.data.local.entity.DetailHeroEntity
import com.example.genshincharactercompose.data.local.entity.HeroEntity
import com.example.genshincharactercompose.data.local.room.HeroDao
import com.example.genshincharactercompose.model.Hero
import com.example.genshincharactercompose.model.FakeHeroDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class HeroRepository(
    private val heroDao : HeroDao
) {
    private val heroes = mutableListOf<Hero>()
//    private val hero = mutableListOf<>()
    init {
        if(heroes.isEmpty()){
            FakeHeroDataSource.dummyHeroes.forEach{
                heroes.add(it)
            }
        }
    }

    fun getHero():Flow<List<Hero>>{
        return flowOf(heroes)
    }

    fun dummySearchHero(query: String): Flow<List<Hero>>{
        return flowOf(heroes.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    fun searchHero(query: String): List<Hero>{
        return FakeHeroDataSource.dummyHeroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteHero():LiveData<List<HeroEntity>>{
        return heroDao.getHero()
    }

    suspend fun saveHero(hero:HeroEntity){
        heroDao.saveHero(hero)
    }

    suspend fun saveDetailHero(heroDetail: DetailHeroEntity){
        heroDao.saveHeroDetail(heroDetail)
    }

    suspend fun deleteHero(name: String){
        heroDao.deleteHero(name)
    }

    suspend fun deleteDetailHero(heroName: String){
        heroDao.deleteDetailHero(heroName)
    }

    fun isFavoriteHero(name: String):LiveData<Boolean>{
        return heroDao.isFavoriteHero(name)
    }

    companion object{
        @Volatile
        private var instance:HeroRepository? = null
        fun getInstance(
            heroDao: HeroDao
        ):HeroRepository =
            instance?: synchronized(this){
                instance ?: HeroRepository(heroDao)
            }.also { instance= it }

    }
}