package uz.space.proverb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProverbDao {
    @Query("SELECT * FROM `proverbs-table`")
    fun getAllProverbs(): LiveData<List<Proverb>>

    @Update
    suspend fun updateProverb(proverb: Proverb)

    @Query("SELECT * FROM `proverbs-table` WHERE proverb LIKE :proverb")
    fun searchData(proverb: String): LiveData<List<Proverb>>
}