package com.example.genshincharactercompose.data
import androidx.lifecycle.LiveData
import com.example.genshincharactercompose.data.local.entity.HeroEntity
import com.example.genshincharactercompose.data.local.room.HeroDao
import com.example.genshincharactercompose.model.DetailHero
import com.example.genshincharactercompose.model.FakeDetailHeroDataSource
import com.example.genshincharactercompose.model.FakeHeroDataSource
import com.example.genshincharactercompose.model.Hero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class HeroRepository(
    private val heroDao : HeroDao
) {
    private val heroes = mutableListOf<Hero>()
    private val detailHero = mutableListOf<DetailHero>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
//    private val hero = mutableListOf<>()
    init {
        if(heroes.isEmpty()){
            FakeHeroDataSource.dummyHeroes.forEach{
                heroes.add(it)
            }
        }
        if(detailHero.isEmpty()){
            FakeDetailHeroDataSource.dummyDetailHeroDataSource.forEach{
                detailHero.add(it)
            }
        }
    }
    fun getHero():Flow<List<Hero>>{
        return flowOf(heroes)
    }

    fun getDetailHero(heroName: String):DetailHero{
        return detailHero.first{
            it.name == heroName
        }
    }

    fun searchHero(query: String): Flow<List<Hero>>{
        return flowOf(heroes.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    fun getFavoriteHero():LiveData<List<HeroEntity>>{
        return heroDao.getHero()
    }

    fun saveHero(hero:HeroEntity){
        coroutineScope.launch (Dispatchers.IO){
            heroDao.saveHero(hero)
        }
    }

    fun deleteHero(name: String){
        coroutineScope.launch (Dispatchers.IO) {
            heroDao.deleteHero(name)
        }
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