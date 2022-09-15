package uz.gita.myeventapp.data.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


object MySharedPreferences {
    private var sharedPreferences: SharedPreferences? = null
    fun initSharedPreference(context: Context): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences =
                context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE)
        }
        return sharedPreferences
    }

    fun putString(key: String?, value: String?) {
        val preferenceEditor = sharedPreferences!!.edit()
        preferenceEditor.putString(key, value)
        preferenceEditor.apply()
    }

    fun putBoolean(key : String,value : Boolean){
        val preferenceEditor  = sharedPreferences!!.edit()
        preferenceEditor.putBoolean(key, value)
        preferenceEditor.apply()
    }

    fun getBoolean(key : String, defaultValue : Boolean) : Boolean{
        return sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return sharedPreferences!!.getString(key, defaultValue)
    }

    fun putInteger(key: String?, value: Int?) {
        val preferenceEditor = sharedPreferences!!.edit()
        preferenceEditor.putInt(key, value!!)
        preferenceEditor.apply()
    }

    fun getInt(key: String?, defaultValue: Int?): Int {
        return sharedPreferences!!.getInt(key, defaultValue!!)
    }
}