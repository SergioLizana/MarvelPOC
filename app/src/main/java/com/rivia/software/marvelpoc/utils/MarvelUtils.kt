package com.rivia.software.marvelpoc.utils

import android.util.Log
import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_PRIVATE_KEY
import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_PUBLIC_KEY
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp

object MarvelUtils {

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    fun generateHash(timestamp: String): String =
        (timestamp + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY).md5()
            .apply {
                Log.d("MD5 Hash", this)
    }
}