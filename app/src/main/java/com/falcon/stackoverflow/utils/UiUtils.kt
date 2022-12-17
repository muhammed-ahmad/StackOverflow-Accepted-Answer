package com.falcon.stackoverflow.utils

import android.R
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.widget.TextView


object UiUtils {

    fun fromHtml(html: String?): Spanned? {
        return if (html == null) {
            SpannableString("")
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun setTypeFace(textView: TextView ,context: Context){
        val type = Typeface.createFromAsset(context.getAssets(), "fonts/mononoki-Regular.ttf")
        textView.setTypeface(type)
    }

}