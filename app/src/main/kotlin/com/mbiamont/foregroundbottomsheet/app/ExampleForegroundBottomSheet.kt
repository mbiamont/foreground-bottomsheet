package com.mbiamont.foregroundbottomsheet.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mbiamont.foregroundbottomsheet.ForegroundBottomSheet
import com.mbiamont.foregroundbottomsheet.app.databinding.DialogExampleBinding

class ExampleForegroundBottomSheet : ForegroundBottomSheet() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogExampleBinding.inflate(inflater)

        binding.btCloseMenu.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}