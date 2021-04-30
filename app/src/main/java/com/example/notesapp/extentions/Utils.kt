package com.example.notesapp.extentions

import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

fun ShapeableImageView.setCornerRadius(
    topRight: Float = 0f,
    topLeft: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setTopLeftCorner(CornerFamily.ROUNDED, topLeft)
        .setTopRightCorner(CornerFamily.ROUNDED, topRight)
        .setBottomLeftCorner(CornerFamily.ROUNDED, bottomLeft)
        .setBottomRightCorner(CornerFamily.ROUNDED, bottomRight)
        .build()
}