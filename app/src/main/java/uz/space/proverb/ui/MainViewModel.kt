package uz.space.proverb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.space.proverb.core.Resource
import uz.space.proverb.data.Proverb

class MainViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<Resource<List<Proverb>>> = MutableLiveData()

    var proverbList: LiveData<Resource<List<Proverb>>> = mutableLiveData

    fun getAllProvebs() {
        _getAll()
    }

    private fun _getAll() {
        mutableLiveData.value = Resource.loading()

        var list: MutableList<Proverb> = mutableListOf()
        for (i in 0 until 10) {
            list.add(Proverb(i + 1, "Proverb $i", "Description $i"))
        }
        if (list.isNotEmpty()) {
            mutableLiveData.value = Resource.success(list)
        }else{
            mutableLiveData.value = Resource.error("Error")
        }

    }

}