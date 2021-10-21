package uz.space.proverb.settings

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class Settings() {

    companion object{
        private const val MY_SETTINGS = "MySettings"
        const val TEXT_SIZE_TITLE = "title"
        const val TEXT_SIZE_DESCRIPTON = "description"
        const val DEFAULT_TEXT_SIZE_TITLE = 18F
        const val DEFAULT_TEXT_SIZE_DESCRIPTION = 16F

    }

    fun setTextSize(key: String, textSize: Float, context: Context) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        preferences.edit().putFloat(key, textSize).apply()
    }

    fun getTextSize(key: String, context: Context): Float {
        val preferences: SharedPreferences =
            context.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)

        return if(key == TEXT_SIZE_TITLE){
            preferences.getFloat(key, 18F)
        }else{
            preferences.getFloat(key, 16F)
        }

    }
}