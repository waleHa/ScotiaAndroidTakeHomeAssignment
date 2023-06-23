package com.scotia.androidtakehomeassigment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageSetter")
fun ImageView.imageSetter(url: String?) {
    if(url != null) {
        Glide.with(this).load(url).centerCrop().into(this)
    }
    else{
        val photoNotFoundReplacement = "https://www.scotiabank.com/content/dam/scotiabank/canada/common/imagery/Scotiabank-future-manifesto-careers-img.png"
        Glide.with(this).load("photoNotFoundReplacement").centerCrop().into(this)
    }
}
