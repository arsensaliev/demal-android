package com.demal.utils.files

import android.content.Context
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ContentFileUtil {

    fun getFileByte(uri: Uri, context: Context): ByteArray? {

        var fileByte: ByteArray? = null
        val steamIn: InputStream? = uri.let { context.contentResolver.openInputStream(it) }

        steamIn?.let {
            fileByte = getBytes(it)
        }
        steamIn?.close()
        return fileByte

    }

    fun getBytes(inputStream: InputStream): ByteArray? {

        val byteBuff = ByteArrayOutputStream()
        val buffSize = 1024
        val buff = ByteArray(buffSize)
        var len = 0
        while (inputStream.read(buff).also { len = it } != -1) {
            byteBuff.write(buff, 0, len)
        }
        return byteBuff.toByteArray()

    }
}