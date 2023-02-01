package com.example.noteapp

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.noteapp.database.ImageInfo

@BindingAdapter("loadImage")
fun bindingImage(imageImageView: ImageView, imageUrl: String?) {
    Glide.with(imageImageView.context).load(imageUrl).into(imageImageView)
}