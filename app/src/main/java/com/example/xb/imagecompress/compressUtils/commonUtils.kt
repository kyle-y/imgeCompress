package com.example.xb.imagecompress.compressUtils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object commonUtils {

    fun compressByQuality(bitmap: Bitmap, sizeKb : Int) : Bitmap{

        var quality = 100
        var bos = ByteArrayOutputStream()

        /*
        质量压缩，当formate为PNG时，为无损压缩，则quality不起作用；
        quality为100时表示不压缩，直接将位图写入输出流；
         */
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)

        while (bos.toByteArray().size / 1024 > sizeKb){
            quality -= 10
            bos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        }

        val bis = ByteArrayInputStream(bos.toByteArray())

        return BitmapFactory.decodeStream(bis)
    }


}