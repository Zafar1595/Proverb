package uz.space.proverb.data

import androidx.lifecycle.LiveData

class ProverbRepository(private val proverbDao: ProverbDao) {

    val readAllData: LiveData<List<Proverb>> = proverbDao.getAllProverbs()

    suspend fun updateProverb(proverb: Proverb) = proverbDao.updateProverb(proverb)

    fun searchDatabase(proverb: String): LiveData<List<Proverb>>{
        return proverbDao.searchData(proverb)
    }

}