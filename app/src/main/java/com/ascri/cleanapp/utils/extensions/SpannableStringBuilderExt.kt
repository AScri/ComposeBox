package com.ascri.cleanapp.utils.extensions

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

private fun SpannableStringBuilder.setSubstringSpan(
    substring: String,
    span: Any
): SpannableStringBuilder {
    val startIndex = this.toString().indexOf(substring)
    if (startIndex == -1) return this

    val endIndex = startIndex + substring.length
    setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return this
}

fun SpannableStringBuilder.underline(substring: String): SpannableStringBuilder =
    setSubstringSpan(substring, UnderlineSpan())

fun SpannableStringBuilder.strikethrough(substring: String): SpannableStringBuilder =
    setSubstringSpan(substring, StrikethroughSpan())

fun SpannableStringBuilder.color(
    substring: String,
    @ColorInt color: Int
): SpannableStringBuilder = setSubstringSpan(substring, ForegroundColorSpan(color))

fun SpannableStringBuilder.color(
    substring: String,
    context: Context,
    @ColorRes colorRes: Int
): SpannableStringBuilder = color(substring, context.getColorQuick(colorRes))

fun SpannableStringBuilder.color(@ColorInt color: Int): SpannableStringBuilder =
    setSubstringSpan(toString(), ForegroundColorSpan(color))

fun SpannableStringBuilder.color(
    context: Context,
    @ColorRes colorRes: Int
): SpannableStringBuilder = color(toString(), context.getColorQuick(colorRes))

fun SpannableStringBuilder.font(
    substring: String,
    context: Context,
    @FontRes font: Int
): SpannableStringBuilder {
    val typeface = ResourcesCompat.getFont(context, font) ?: return this
    return setSubstringSpan(substring, CustomTypefaceSpan(typeface))
}

fun SpannableStringBuilder.size(substring: String, proportion: Float): SpannableStringBuilder =
    setSubstringSpan(substring, RelativeSizeSpan(proportion))

/**
 * Makes [substring] clickable. Remember to assign [LinkMovementMethod] to
 * [TextView.setMovementMethod] so the [TextView] can handle link clicks.
 */
fun SpannableStringBuilder.clickable(
    substring: String,
    underline: Boolean,
    onClick: (String) -> Unit
): SpannableStringBuilder {
    val span = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick.invoke(substring)
        }

        // by default, making a span clickable underlines text
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = underline
        }
    }
    return setSubstringSpan(substring, span)
}

class CustomTypefaceSpan(private val newType: Typeface) : TypefaceSpan("") {
    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    companion object {
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = tf
        }
    }
}