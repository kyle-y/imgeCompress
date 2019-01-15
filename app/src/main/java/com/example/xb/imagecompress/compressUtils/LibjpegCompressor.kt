package com.example.xb.imagecompress.compressUtils

import android.graphics.Bitmap

object LibjpegCompressor {
    init{
        System.loadLibrary("jpeg")
    }
}