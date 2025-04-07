package io.chaldeaprjkt.gamespace.data

import android.content.Context
import androidx.preference.PreferenceManager
import javax.inject.Inject

class AppSettings @Inject constructor(private val context: Context) {

    private val db by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    var stayAwake
        get() = db.getBoolean(KEY_STAY_AWAKE, false)
        set(value) = db.edit().putBoolean(KEY_STAY_AWAKE, value).apply()

    var noAutoBrightness
        get() = db.getBoolean(KEY_AUTO_BRIGHTNESS_DISABLE, true)
        set(it) = db.edit().putBoolean(KEY_AUTO_BRIGHTNESS_DISABLE, it).apply()

    var noThreeScreenshot
        get() = db.getBoolean(KEY_3SCREENSHOT_DISABLE, false)
        set(it) = db.edit().putBoolean(KEY_3SCREENSHOT_DISABLE, it).apply()

    var noAdbEnabled
        get() = db.getBoolean(KEY_ADB_DISABLE, false)
        set(it) = db.edit().putBoolean(KEY_ADB_DISABLE, it).apply()

    var ringerMode: Int
        get() = db.getString(KEY_RINGER_MODE, "3")?.toIntOrNull() ?: 3
        set(value) = db.edit().putString(KEY_RINGER_MODE, value.toString()).apply()

    companion object {
        const val KEY_STAY_AWAKE = "gamespace_stay_awake"
        const val KEY_AUTO_BRIGHTNESS_DISABLE = "gamespace_auto_brightness_disabled"
        const val KEY_3SCREENSHOT_DISABLE = "gamespace_tfgesture_disabled"
        const val KEY_ADB_DISABLE = "gamespace_adb_disabled"
        const val KEY_RINGER_MODE = "gamespace_ringer_mode"
    }
}
