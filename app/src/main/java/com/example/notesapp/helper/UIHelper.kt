package com.example.notesapp.helper

import android.content.Context
import android.widget.Toast

var toast: Toast? = null
fun showToast(context: Context, message: String){
    if (toast != null) toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}