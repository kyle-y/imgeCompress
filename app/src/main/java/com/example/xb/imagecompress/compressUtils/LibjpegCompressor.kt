package com.example.xb.imagecompress.compressUtils

import android.graphics.Bitmap

object LibjpegCompressor {
    init{
        System.loadLibrary("libjpeg")
    }

    fun compress(bitmap: Bitmap, quality: Int, outfile: String) {
        nativeCompress(bitmap, quality, outfile)
    }

    private external fun nativeCompress(bitmap: Bitmap, quality: Int, outfile: String): Boolean
}