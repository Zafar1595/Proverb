package uz.space.proverb.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proverbs-table")
data class Proverb(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "proverb")
    var proverb: String,
    @ColumnInfo(name = "favorit")
    var favorit: Int,
    @ColumnInfo(name = "all_text")
    var allText: String
)