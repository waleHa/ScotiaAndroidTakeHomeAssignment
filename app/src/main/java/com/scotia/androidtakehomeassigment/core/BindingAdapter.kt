package com.scotia.androidtakehomeassigment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Custom [BindingAdapter] for setting an image from a URL using Glide library.
 *
 * @param url the URL of the image
 */
@BindingAdapter("imageSetter")
fun ImageView.imageSetter(url: String?) {
    if (url != null) {
        // Load the image from the provided URL using Glide library
        Glide.with(this).load(url).centerCrop().into(this)
    } else {
        // If the URL is null, load a default replacement image
        val photoNotFoundReplacement = "https://www.scotiabank.com/content/dam/scotiabank/canada/common/imagery/Scotiabank-future-manifesto-careers-img.png"
        Glide.with(this).load(photoNotFoundReplacement).centerCrop().into(this)
    }
}

