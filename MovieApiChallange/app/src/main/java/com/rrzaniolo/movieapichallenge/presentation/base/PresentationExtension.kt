package com.rrzaniolo.movieapichallenge.presentation.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.rrzaniolo.movieapichallenge.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
 fun showErrorDialog(context: Context, onTryAgain: ()->Unit, onCancel: ()->Unit){
    val errorDialog = AlertDialog.Builder(context)
        .setTitle(R.string.errorTitle)
        .setMessage(R.string.errorMessage)
        .setCancelable(true)
        .setPositiveButton(R.string.tryAgain) { _, _ -> onTryAgain.invoke() }
        .setNegativeButton(R.string.cancel) { _, _ -> onCancel.invoke() }
        .create()

    errorDialog
            .setOnShowListener {
                errorDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.darkPurple))
                errorDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.darkPurple))
            }
    errorDialog
        .show()
}

fun View.isVisible(isVisible: Boolean){
    this.visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun String.toDate(): String? {
    val fromDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val toDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return fromDate.parse(this)?.let { date ->
        toDate.format(date)
    }
}

fun Drawable.tint(@ColorInt color: Int): Drawable{
    this.setTint(color)
    return  this
}

fun Drawable.tint(@ColorRes colorId: Int, context: Context): Drawable{
    ContextCompat.getColor(context, colorId).also {
        return this.tint(it)
    }
}