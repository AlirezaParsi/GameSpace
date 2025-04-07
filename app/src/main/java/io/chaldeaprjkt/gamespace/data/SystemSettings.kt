package io.chaldeaprjkt.gamespace.data

import android.content.Context
import java.io.DataOutputStream

class SystemSettings(private val context: Context) {

    var autoBrightness: Boolean?
        get() = try {
            val result = execCommand("settings get system screen_brightness_mode").trim()
            result == "1" // 1 = auto, 0 = manual
        } catch (e: Exception) {
            null
        }
        set(value) {
            value?.let {
                execRootCommand("settings put system screen_brightness_mode ${if (it) 1 else 0}")
            }
        }

    var headsup: Boolean?
        get() = try {
            val result = execCommand("settings get secure heads_up_notifications_enabled").trim()
            result == "1"
        } catch (e: Exception) {
            null
        }
        set(value) {
            value?.let {
                execRootCommand("settings put secure heads_up_notifications_enabled ${if (it) 1 else 0}")
            }
        }

    var threeScreenshot: Int
        get() = try {
            execCommand("settings get system three_finger_gesture").trim().toIntOrNull() ?: 0
        } catch (e: Exception) {
            0
        }
        set(value) {
            execRootCommand("settings put system three_finger_gesture $value")
        }

    var adbEnabled: Boolean?
        get() = try {
            val result = execCommand("settings get global adb_enabled").trim()
            result == "1"
        } catch (e: Exception) {
            null
        }
        set(value) {
            value?.let {
                execRootCommand("settings put global adb_enabled ${if (it) 1 else 0}")
            }
        }

    private fun execCommand(command: String): String {
        val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
        return process.inputStream.bufferedReader().use { it.readText() }
    }

    private fun execRootCommand(command: String) {
        try {
            val process = Runtime.getRuntime().exec("su")
            val output = DataOutputStream(process.outputStream)
            output.writeBytes("$command\n")
            output.writeBytes("exit\n")
            output.flush()
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
