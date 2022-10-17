package com.ascri.cleanapp.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.res.ColorStateList
import android.content.res.Resources.getSystem
import android.graphics.*
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.ascri.cleanapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.button.MaterialButton
import kotlin.math.roundToInt

private const val BUBBLE_BURST_TIME = 350L
private const val BUBBLE_POPUP_TIME = 400L
val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
val Float.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()
val Float.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
val Int.sp: Int get() = (this * getSystem().displayMetrics.scaledDensity).toInt()

inline fun <T : View> T.afterLayout(crossinline function: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                function()
            }
        }
    })
}

fun EditText.hideKeyboardOnDone(activity: Activity) {
    setOnEditorActionListener { v, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            activity.hideSoftKeyboard(v)
            v.clearFocus()
        }
        return@setOnEditorActionListener false
    }
}

fun EditText.hideKeyboardOnLoseFocus(activity: Activity) {
    setOnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            activity.hideSoftKeyboard(v)
        }
    }
}

fun View.hideKeyboardOnFocus(activity: Activity) {
    setOnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            activity.hideSoftKeyboard(v)
        }
    }
}

fun View.hideKeyboardOnClick(activity: Activity) {
    setOnClickListener {
        activity.hideSoftKeyboard(it)
        it.findFocus()?.clearFocus()
    }
}

fun View.getBitmap(width: Int = 0, height: Int = 0, downSampleIndex: Float = 1f): Bitmap {
    val viewBitmap: Bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(viewBitmap)
    this.draw(canvas)
    return Bitmap.createScaledBitmap(
        viewBitmap,
        ((if (width == 0) this.width else width) * downSampleIndex).toInt(),
        ((if (height == 0) this.height else height) * downSampleIndex).toInt(),
        false
    )
}

fun View.getBitmapWithOverlay(@ColorRes overlayColor: Int, downSampleIndex: Float = 1f): Bitmap {
    val viewBitmap: Bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(viewBitmap)
    this.draw(canvas)
    canvas.drawColor(
        this.resources.getColor(overlayColor, this.context.theme),
        PorterDuff.Mode.SRC_OVER
    )
    return Bitmap.createScaledBitmap(
        viewBitmap,
        (width * downSampleIndex).toInt(),
        (height * downSampleIndex).toInt(),
        false
    )
}

fun View.startPopupAnimation(bubbleSize: Int) {
    ValueAnimator.ofInt(bubbleSize.px / 4, (bubbleSize.px * 1.1).roundToInt()).apply {
        addUpdateListener {
            this@startPopupAnimation.layoutParams.height = it.animatedValue as Int
            this@startPopupAnimation.layoutParams.width = it.animatedValue as Int
            this@startPopupAnimation.requestLayout()
        }
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                ValueAnimator.ofInt(
                    (bubbleSize.px * 1.1).roundToInt(),
                    bubbleSize.px
                ).apply {
                    addUpdateListener {
                        this@startPopupAnimation.layoutParams.height = it.animatedValue as Int
                        this@startPopupAnimation.layoutParams.width = it.animatedValue as Int
                        this@startPopupAnimation.requestLayout()
                    }
                    duration = BUBBLE_POPUP_TIME / 2
                    start()
                }
            }
        })
        duration = BUBBLE_POPUP_TIME
        start()
    }
}

fun View.getValueAnimator(
    forward: Boolean = true,
    duration: Long,
    interpolator: TimeInterpolator,
    updateListener: (progress: Float) -> Unit
): ValueAnimator {
    val animator =
        if (forward) ValueAnimator.ofFloat(0f, 1f)
        else ValueAnimator.ofFloat(1f, 0f)
    animator.addUpdateListener { updateListener(it.animatedValue as Float) }
    animator.duration = duration
    animator.interpolator = interpolator
    return animator
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun Animation.onAnimationEnd(onAnimationEnded: (animation: Animation?) -> Unit): Animation {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnded.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
    return this
}

fun Animation.onAnimationStart(onAnimationStart: (animation: Animation?) -> Unit): Animation {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationStart.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
    return this
}

fun Animation.onAnimationRepeat(onAnimationRepeat: (animation: Animation?) -> Unit): Animation {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
        }

        override fun onAnimationRepeat(animation: Animation?) {
            onAnimationRepeat.invoke(animation)
        }
    })
    return this
}

fun BottomSheetBehavior<View>.expand() {
    this.state = STATE_EXPANDED
}

fun BottomSheetBehavior<View>.collapse() {
    this.state = STATE_COLLAPSED
}

fun BottomSheetBehavior<View>.hide() {
    this.state = STATE_HIDDEN
}

fun RecyclerView.Adapter<*>.getBottomPosition(): Int {
    return (this.itemCount - 1).let {
        if (it >= 0) it
        else 0
    }
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun MaterialButton.enable(enable: Boolean) {
    isEnabled = enable
    strokeWidth = if (enable) 0 else 2.px
    backgroundTintList =
        ColorStateList.valueOf(
            resources.getColor(
                if (enable) R.color.color_white else R.color.color_gray,
                context.theme
            )
        )
}

fun TextView.setClickableLinkSpan(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = resources.getColor(R.color.color_gray, context.theme)
                textPaint.isUnderlineText = false
            }

            override fun onClick(view: View) {
                link.second.onClick(view)
            }
        }
        val startIndexOfLink = this.text.toString().indexOf(link.first)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    movementMethod = LinkMovementMethod.getInstance()
    setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun TextView.setBoldSpan(text: String) {
    val spannableString = SpannableString(this.text)
    val startIndexOfLink = this.text.toString().indexOf(text)
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD), startIndexOfLink, startIndexOfLink + text.length,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )
    setText(spannableString, TextView.BufferType.SPANNABLE)
}