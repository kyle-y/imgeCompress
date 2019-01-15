package com.example.xb.imagecompress

import android.Manifest
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.example.xb.imagecompress.compressUtils.PickUtils
import android.R.attr.data
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.SeekBar
import com.example.xb.imagecompress.compressUtils.CommonUtils
import java.io.File


class MainActivity : AppCompatActivity() {

    private var imagePath = ""
    private var imageBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }

        choose_text.setOnClickListener {
            openAlbum()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                quality_text.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        compress_text.setOnClickListener {
            val quality = seekBar.progress
            val tempFile = File("${cacheDir.absolutePath}/${System.currentTimeMillis()}.jpg")
            imageBitmap?.let {
                CommonUtils.compressByQuality(it, quality, tempFile.absolutePath)
            }
            val imageBitmap2 = BitmapFactory.decodeFile(tempFile.absolutePath)
            afterImage.setImageBitmap(imageBitmap2)
            afterText.text = "宽高：${imageBitmap2.width}x${imageBitmap2.height}，大小：${getSize(imageBitmap2)},文件：${(tempFile.length().toFloat()/1024).toInt()}KB"
        }
    }


    // 打开本地相册
    private fun openAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        this.startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101){
            val uri = data?.data//得到uri，后面就是将uri转化成file的过程。
            imagePath = PickUtils.getPath(this@MainActivity, uri)
            imageBitmap = BitmapFactory.decodeFile(imagePath)
            beforeImage.setImageBitmap(imageBitmap)
            beforeText.text = "宽高：${imageBitmap?.width}*${imageBitmap?.height}，大小：${getSize(imageBitmap!!)},文件：${(File(imagePath).length().toFloat()/1024).toInt()}KB"
        }
    }

    private fun getSize(bitmap: Bitmap) : String{
        val size = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap.allocationByteCount
        } else {
            bitmap.byteCount
        }
        return "${(size / 1024f).toInt()}KB"
    }

}
