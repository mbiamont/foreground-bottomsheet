package com.mbiamont.foregroundbottomsheet

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

internal fun View.animateToForeground(onEnd: () -> Unit = {}) {
    val animY = ObjectAnimator.ofFloat(this, "y", 0f)
        .setDuration(DURATION_ANIM_TRANSLATION)
        .apply { startDelay = DELAY_ANIM_TRANSLATION }

    val animScaleX = ObjectAnimator.ofFloat(this, "scaleX", SCALE_X, 1f)
        .setDuration(DURATION_ANIM_SCALE)
        .apply { startDelay = DELAY_ANIM_SCALE }

    val animScaleY = ObjectAnimator.ofFloat(this, "scaleY", SCALE_Y, 1f)
        .setDuration(DURATION_ANIM_SCALE)
        .apply { startDelay = DELAY_ANIM_SCALE }

    playAnimations(animY, animScaleX, animScaleY) {
        onEnd()
    }
}

internal fun View.animateToBackground(onEnd: () -> Unit = { }) {
    val translationY = resources.getDimensionPixelSize(R.dimen.foregroundContentViewTranslationY).toFloat()

    val animScaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, SCALE_Y)
        .setDuration(DURATION_ANIM_SCALE)
        .apply { startDelay = DELAY_ANIM_SCALE }

    val animScaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, SCALE_X)
        .setDuration(DURATION_ANIM_SCALE)
        .apply { startDelay = DELAY_ANIM_SCALE }

    val animY = ObjectAnimator.ofFloat(this, "y", translationY)
        .setDuration(DURATION_ANIM_TRANSLATION)
        .apply { startDelay = DELAY_ANIM_TRANSLATION }

    playAnimations(animScaleY, animScaleX, animY) {
        onEnd()
    }
}

internal inline fun playAnimations(
    vararg animations: Animator,
    crossinline onEnd: (animator: Animator) -> Unit = {}
) = AnimatorSet().apply {
    playTogether(*animations)
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) = Unit
        override fun onAnimationEnd(animator: Animator) = onEnd(animator)
        override fun onAnimationCancel(animator: Animator) = Unit
        override fun onAnimationStart(animator: Animator) = Unit
    })
    start()
}

private const val DURATION_ANIM = 200L
private const val DURATION_ANIM_SCALE = DURATION_ANIM / 2 //200
private const val DELAY_ANIM_SCALE = DURATION_ANIM_SCALE //100

private const val DURATION_ANIM_TRANSLATION = DURATION_ANIM / 2 //200
private const val DELAY_ANIM_TRANSLATION = DURATION_ANIM_TRANSLATION / 2 //100

private const val SCALE_X = 0.85f
private const val SCALE_Y = 0.85f
