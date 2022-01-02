package com.mbiamont.foregroundbottomsheet.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbiamont.foregroundbottomsheet.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSimpleBottomSheet.setOnClickListener {
            ExampleForegroundBottomSheet().show(supportFragmentManager, ExampleForegroundBottomSheet::class.java.name)
        }

        binding.btUnDraggableBottomSheet.setOnClickListener {
            ExampleUnDraggableForegroundBottomSheet().show(supportFragmentManager, ExampleForegroundBottomSheet::class.java.name)
        }
    }
}