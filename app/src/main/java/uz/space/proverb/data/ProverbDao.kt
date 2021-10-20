package uz.space.proverb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProverbDao {
    @Query("SELECT * FROM `proverbs-table`")
    fun getAllProverbs(): LiveData<List<Proverb>>
}