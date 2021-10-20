package uz.space.proverb.data

import androidx.lifecycle.LiveData

class ProverbRepository(private val proverbDao: ProverbDao) {

    val readAllData: LiveData<List<Proverb>> = proverbDao.getAllProverbs()

}