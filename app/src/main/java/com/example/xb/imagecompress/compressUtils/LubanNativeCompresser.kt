package com.example.xb.imagecompress.compressUtils

import android.graphics.Bitmap


object LubanNativeCompresser {

    // Used to load the 'native-lib' library on application startup.
    init {
        System.loadLibrary("luban")
    }

    fun compress(bitmap: Bitmap, quality: Int, outfile: String) {
        nativeCompress(bitmap, quality, outfile)
    }

    private external fun nativeCompress(bitmap: Bitmap, quality: Int, outfile: String): Boolean
}