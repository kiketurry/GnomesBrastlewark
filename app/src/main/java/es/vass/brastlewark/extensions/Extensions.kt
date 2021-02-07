package es.vass.brastlewark.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import es.vass.brastlewark.R
import es.vass.brastlewark.ui.base.BaseActivity
import es.vass.brastlewark.ui.base.BaseFragment
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible(show: Boolean) {
    if (show) {
        visible()
    } else {
        gone()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun BaseActivity<*>.toast(message: String) {
    baseContext.toast(message)
}

fun BaseFragment<*>.toast(message: String) {
    context?.toast(message)
}

fun ImageView.glideWithHeaders(context: Context, urlImage: String, ) {
    val glideUrl = GlideUrl(
        urlImage, LazyHeaders.Builder().addHeader(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
        ).build()
    )

    Glide.with(context)
        .load(glideUrl)
        .placeholder(R.drawable.gnome_loading)
        .apply(RequestOptions().timeout(5000))
        .into(this)
}