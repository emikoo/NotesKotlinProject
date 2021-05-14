package com.example.notesapp.helper

import android.content.Context
import android.view.View
import android.widget.Toast

var toast: Toast? = null
fun showPerfectToast(context: Context, message: String) {
    if (toast != null) toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG).apply {
        show()
    }
}

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this. visibility = View.GONE
}