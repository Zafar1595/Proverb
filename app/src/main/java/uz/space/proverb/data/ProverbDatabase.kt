package uz.space.proverb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Proverb::class], version = 1)
abstract class ProverbDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: ProverbDatabase? = null

        fun getInstance(context: Context): ProverbDatabase {
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }else {
                synchronized(this) {
                    return Room.databaseBuilder(
                        context,
                        ProverbDatabase::class.java,
                        "proverb-database.db"
                    )
                        .createFromAsset("proverb-database.db")
                        .build()
                }
            }
        }
    }

    abstract fun dao(): ProverbDao

}