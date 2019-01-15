package com.example.xb.imagecompress.compressUtils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import top.zibin.luban.turbo.TurboCompressor
import java.io.*


object CommonUtils {

    /**
     * 质量压缩
     *
     * 其基本原理是相近相似像素由同一像素*n来表示，同时开启哈夫曼编码会减少编码后的二进制文件大小
     * 总体来说，这种压缩属于有损压缩，压缩后生成文件大小会比较小，但解析成bitmap所占用的内存并没有改变，因为像素总个数没有改变
     * 但将压缩后的文件重新加载为bitmap时，会发现为了压缩后大小，已经改变了某些相邻相近的像素的色值
     * @see Bitmap#getAllocationByteCount()
     * @see Bitmap#getByteCount()
     *
     * 其底层调用 compressNative（使用Skia引擎，对jpeg的处理基于libjpeg开源库，对png的处理则是基于libpng，7.0之前压缩效果比较差，7.0后开启了哈夫曼编码，在压缩质量和大小上都好很多）
     * 针对7.0之前压缩差的问题，可以自己编译libjpeg库来进行改进,{@link #https://blog.csdn.net/sakuramashiro/article/details/79182239}
     */
    fun compressByQuality(bitmap: Bitmap, sizeKb: Int): Bitmap {

        var quality = 100
        var bos = ByteArrayOutputStream()

        /*
        质量压缩，当formate为PNG时，为无损压缩，则quality不起作用；
        quality为100时表示不压缩，直接将位图写入输出流；
         */
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)

        while (bos.toByteArray().size / 1024 > sizeKb) {
            quality -= 10
            bos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
            if (quality == 0) break
        }

        val bis = ByteArrayInputStream(bos.toByteArray())

        return BitmapFactory.decodeStream(bis)
    }


    fun compressByQuality(bitmap: Bitmap, quality : Int, outFilePath : String){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
            val file = File(outFilePath)
            if (!file.exists()) file.mkdir()
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream)
            fileOutputStream.flush()
        }else{
            TurboCompressor.compress(bitmap, quality, outFilePath)
        }
    }

    /**
     * 从本地文件高效加载bitmap（为什么高效？可以防止一开始将大图加载的内存中造成OOM）
     * 因为是针对bitmap像素级别的操作，可以想象肯定是有损压缩，可以减少图片bitmap所占的内存，因为宽高减小了
     * 其内部采用邻近采样算法，2的n次方个邻近像素选择一个像素，抛弃其他像素，强行将图片的宽高缩小，这种操作极易造成图片失真
     */
    fun decodeBitmapFromFileBySize(imageFile: File, reqWidth: Int, reqHeight: Int): Bitmap {

        //得到原始图片的宽高
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(imageFile.absolutePath, options)


        //根据需求宽高，得到目标宽高
        var actualHeight = options.outHeight
        var actualWidth = options.outWidth

        var imgRatio = actualWidth.toFloat() / actualHeight.toFloat()
        val maxRatio = reqWidth / reqHeight

        //如果比需求大，需要缩小
        if (actualHeight > reqHeight || actualWidth > reqWidth) {
            when {
                imgRatio < maxRatio -> {  //原始图片高度较大，此时以图片高度为基准，进行缩小，缩小到需求大小后宽上肯定有空白
                    imgRatio = reqHeight / actualHeight.toFloat()
                    actualWidth = (imgRatio * actualWidth).toInt()
                    actualHeight = reqHeight
                }
                imgRatio > maxRatio -> {  //原始图片宽度较大，此时以图片宽度为基准，进行缩小，缩小到需求大小后高上肯定有空白
                    imgRatio = reqWidth / actualWidth.toFloat()
                    actualHeight = (imgRatio * actualHeight).toInt()
                    actualWidth = reqWidth
                }
                else -> { //二者比例相等，直接缩小，缩小到需求大小后宽高上都没有空白
                    actualHeight = reqHeight
                    actualWidth = reqWidth
                }
            }

            //根据目标宽高得到最接近的缩放比上限，比如需要缩放1/3 则会得到2，而不是4
            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
        }else{
            //如果比需求小，则直接加载，不需要改变
        }

        options.inJustDecodeBounds = false
        options.inDither = false
        options.inPurgeable = true
        options.inInputShareable = true
        options.inTempStorage = ByteArray(16 * 1024)
        //直接加载出目标大小的bitmap
        try {
            bmp = BitmapFactory.decodeFile(imageFile.absolutePath, options)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        return bmp
    }

    /**
     * 因为inSampleSize是2的次幂，所以这里尽量往上取，如本身需要1/3的缩放即可打到目标值，这里就会得到inSampleSize = 2，即只缩放1/2
     * 保证刚刚好>=目标宽高
     */
    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        var inSampleSize = 1

        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            val halfWidth = options.outWidth / 2
            val halfHeight = options.outHeight / 2
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    /**
     * 如需要1/3即可，这里会得到4,保证肯定<=目标宽高
     * 这个一般不要用，会导致图片压缩过小，失真
     */
    private fun calculateInSampleSize2(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        var inSampleSize = 1

        val width = options.outWidth
        val height = options.outHeight

        while ((height / inSampleSize) > reqHeight && (width / inSampleSize) > reqWidth) {
            inSampleSize *= 2
        }
        return inSampleSize
    }

    /**
     * 鲁班压缩逆推的类微信算法，具体原理为：
     *  1，判断图片比例值，是否处于以下区间内；
            [1, 0.5625) 即图片处于 [1:1 ~ 9:16) 比例范围内
            [0.5625, 0.5) 即图片处于 [9:16 ~ 1:2) 比例范围内
            [0.5, 0) 即图片处于 [1:2 ~ 1:∞) 比例范围内
        2，判断图片最长边是否过边界值；
            [1, 0.5625) 边界值为：1664 * n（n=1）, 4990 * n（n=2）, 1280 * pow(2, n-1)（n≥3）
            [0.5625, 0.5) 边界值为：1280 * pow(2, n-1)（n≥1）
            [0.5, 0) 边界值为：1280 * pow(2, n-1)（n≥1）
        3，计算压缩图片实际边长值，以第2步计算结果为准，超过某个边界值则：width / pow(2, n-1)，height/pow(2, n-1)
        4，计算压缩图片的实际文件大小，以第2、3步结果为准，图片比例越大则文件越大。
            size = (newW * newH) / (width * height) * m；
            [1, 0.5625) 则 width & height 对应 1664，4990，1280 * n（n≥3），m 对应 150，300，300；
            [0.5625, 0.5) 则 width = 1440，height = 2560, m = 200；
            [0.5, 0) 则 width = 1280，height = 1280 / scale，m = 500；注：scale为比例值
        5，判断第4步的size是否过小
            [1, 0.5625) 则最小 size 对应 60，60，100
            [0.5625, 0.5) 则最小 size 都为 100
            [0.5, 0) 则最小 size 都为 100
        6，将前面求到的值压缩图片 width, height, size 传入压缩流程，压缩图片直到满足以上数值
     */
    private fun calculateInSampleSizeByLuban(srcWidth : Int, srcHeight : Int) : Int{
        val tempWidth = if (srcWidth % 2 == 1) srcWidth + 1 else srcWidth
        val tempHeight = if (srcHeight % 2 == 1) srcHeight + 1 else srcHeight

        val longSide = Math.max(tempWidth, tempHeight)
        val shortSide = Math.min(tempWidth, tempHeight)

        val scale = shortSide.toFloat() / longSide
        return if (scale <= 1 && scale > 0.5625) {
            if (longSide < 1664) {
                1
            } else if (longSide < 4990) {
                2
            } else if (longSide in 4991..10239) {
                4
            } else {
                if (longSide / 1280 == 0) 1 else longSide / 1280
            }
        } else if (scale <= 0.5625 && scale > 0.5) {
            if (longSide / 1280 == 0) 1 else longSide / 1280
        } else {
            Math.ceil(longSide / (1280.0 / scale)).toInt()
        }
    }

    /**
     * 对bimap进行缩放，如果不需要缩放，则返回原始bitmap，缩放后会产生新的bitmap，此时将原始bitmap释放掉
     * 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
     *
     * 采用矩阵变换进行压缩，其原理为双线性采样压缩，会将某个像素参考附近相关点，生成一个过渡色的像素
     *
     * 另外，这个方法在长宽比不同时，会把图片拉伸；却会重新创建bitmap，效果和效率都不高，所以谨慎使用
     */
    private fun createScaleBitmap(src: Bitmap, dstWidth: Int, dstHeight: Int): Bitmap {
        val dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false)
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle() // 释放Bitmap的native像素数组
        }
        return dst
    }
}