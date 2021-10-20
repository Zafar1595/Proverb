package uz.space.proverb.ui

import android.app.Application
import androidx.lifecycle.*
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

}