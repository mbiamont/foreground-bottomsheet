package com.mbiamont.foregroundbottomsheet.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mbiamont.foregroundbottomsheet.ForegroundBottomSheet
import com.mbiamont.foregroundbottomsheet.app.databinding.DialogExampleBinding

class ExampleUnDraggableForegroundBottomSheet : ForegroundBottomSheet() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogExampleBinding.inflate(inflater)

        binding.btCloseMenu.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onCreateBottomSheetBehavior(behavior: BottomSheetBehavior<View>) {
        super.onCreateBottomSheetBehavior(behavior)
        behavior.isDraggable = false
    }
}