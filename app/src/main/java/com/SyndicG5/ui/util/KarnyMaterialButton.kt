package com.SyndicG5.ui.util

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.SyndicG5.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class KarnyMaterialButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialButton(context, attrs)


fun KarnyMaterialButton.updateEnabledState(enabled: Boolean) {
    apply {
        if (enabled) {
            isEnabled = true
            setBackgroundColor(ContextCompat.getColor(context, R.color.primary))
        } else {
            isEnabled = false
            setBackgroundColor(ContextCompat.getColor(context, R.color.primary_warm_grey_five))
        }
    }
}

fun ExtendedFloatingActionButton.updateEnabledState(enabled: Boolean) {
    apply {
        if (enabled) {
            isEnabled = true
            setBackgroundColor(ContextCompat.getColor(context, R.color.primary))
        } else {
            isEnabled = false
            setBackgroundColor(ContextCompat.getColor(context, R.color.primary_warm_grey_five))
        }
    }
}
