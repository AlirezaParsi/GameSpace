package io.chaldeaprjkt.gamespace.data

import android.media.AudioManager
import androidx.annotation.Keep

@Keep
data class SessionState(
    var packageName: String,
    var autoBrightness: Boolean? = null,
    var threeScreenshot: Int = 0,
    var ringerMode: Int = AudioManager.RINGER_MODE_NORMAL,
    var adbEnabled: Boolean? = null
)
