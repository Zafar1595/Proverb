package uz.space.proverb.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.space.proverb.data.Proverb
import uz.space.proverb.data.ProverbDatabase
import uz.space.proverb.data.ProverbRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Proverb>>
    private val repository: ProverbRepository
    init {
        val proverbDao = ProverbDatabase.getInstance(application).dao()
        repository = ProverbRepository(proverbDao)
        readAllData = repository.readAllData
    }

    fun selectFavorit(proverb: Proverb) = GlobalScope.launch {
        repository.updateProverb(proverb)
    }

}