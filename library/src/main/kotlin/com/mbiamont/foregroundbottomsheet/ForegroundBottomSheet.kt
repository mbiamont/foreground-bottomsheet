package com.mbiamont.foregroundbottomsheet

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


open class ForegroundBottomSheet : BottomSheetDialogFragment() {
    private var previousStatusBarColor: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ForegroundBottomSheetDialogTheme)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.window?.onShowBottomSheet()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = super.onCreateDialog(savedInstanceState).prepare()

    override fun onDismiss(dialog: DialogInterface) {
        activity?.window?.onHideBottomSheet()
        super.onDismiss(dialog)
    }

    @CallSuper
    protected open fun onCreateBottomSheetBehavior(behavior: BottomSheetBehavior<View>) {
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
    }

    private fun Dialog.prepare(): Dialog = apply {
        window?.attributes?.windowAnimations = R.style.DialogAnimation

        setOnShowListener {
            val bottomSheet = (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val height = Resources.getSystem().displayMetrics.heightPixels

            with(bottomSheet as FrameLayout) {
                layoutParams?.height = height
                requestLayout()
            }

            onCreateBottomSheetBehavior(BottomSheetBehavior.from(bottomSheet))
        }
    }

    private fun Window.onShowBottomSheet() {
        val decorView = decorView as ViewGroup
        val contentView = decorView.getChildAt(0) as ViewGroup

        previousStatusBarColor = statusBarColor

        val innerContentView = contentView.getChildAt(1)

        val cornerBackground = MaterialShapeDrawable(
            ShapeAppearanceModel()
                .toBuilder()
                .setAllCornerSizes(resources.getDimension(R.dimen.cornerRadiusContentView))
                .build()
        )
        val decorViewBackgroundColor = ContextCompat.getColor(context, R.color.decorViewBackgroundColor)
        decorView.setBackgroundColor(decorViewBackgroundColor)

        contentView.animateToBackground {
            innerContentView.clipToOutline = true
            ViewCompat.setBackground(innerContentView, cornerBackground)
            statusBarColor = decorViewBackgroundColor
        }
    }

    private fun Window.onHideBottomSheet() {
        val contentView = decorView.let { it as ViewGroup }.getChildAt(0).let { it as ViewGroup }

        contentView.animateToForeground {
            ViewCompat.setBackground(contentView.getChildAt(1), null)
            previousStatusBarColor?.let { statusBarColor = it }
        }
    }

}