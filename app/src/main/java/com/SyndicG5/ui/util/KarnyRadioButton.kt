package com.SyndicG5.ui.util

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.SyndicG5.R
import com.SyndicG5.databinding.ImmeubleLayoutBinding

class KarnyRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ImmeubleLayoutBinding.inflate(LayoutInflater.from(context), this)
    private var callback: ((Boolean) -> Unit)? = null
    private var type: Int
    private var smallRadioButtonMinHeight = 35
    private var mediumRadioButtonMinHeight = 50
    private var largeRadioButtonMinHeight = 65
    private var mediumRadioButtonTextSize = 16f
    private var displayMetrics: DisplayMetrics? = null

    enum class KarnyRadioButtonType {
        LARGE,
        MEDIUM,
        SMALL
    }

    init {
        type = 2

        displayMetrics = resources.displayMetrics
        smallRadioButtonMinHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            smallRadioButtonMinHeight.toFloat(),
            displayMetrics
        ).toInt()

        mediumRadioButtonMinHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            mediumRadioButtonMinHeight.toFloat(),
            displayMetrics
        ).toInt()

        largeRadioButtonMinHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            largeRadioButtonMinHeight.toFloat(),
            displayMetrics
        ).toInt()

        mediumRadioButtonTextSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            mediumRadioButtonTextSize,
            displayMetrics
        )

        layoutDirection = LAYOUT_DIRECTION_LOCALE
        setupView()
    }


    private fun setupView() {

        setOnClickListener {
            val isChecked = binding.radioButton.isChecked
            if (isChecked) {
                binding.container.setBackgroundResource(R.drawable.shop_radio_btn_unselected)
            } else {
                binding.container.setBackgroundResource(R.drawable.shop_radio_btn_selected)
            }
            binding.radioButton.isChecked = !isChecked
        }
        binding.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.setButtonDrawable(R.drawable.ic_radio_button_checked)
                binding.container.setBackgroundResource(R.drawable.shop_radio_btn_selected)
            } else {
                buttonView.setButtonDrawable(R.drawable.ic_radio_button_unchecked)
                binding.container.setBackgroundResource(R.drawable.shop_radio_btn_unselected)
            }
            callback?.invoke(isChecked)
        }

        when (type) {
            KarnyRadioButtonType.LARGE.ordinal + 1 -> {
                binding.container.minimumHeight = largeRadioButtonMinHeight

            }
            KarnyRadioButtonType.MEDIUM.ordinal + 1 -> {
                binding.container.minimumHeight = mediumRadioButtonMinHeight
            }
            KarnyRadioButtonType.SMALL.ordinal + 1 -> {
                binding.container.minimumHeight = smallRadioButtonMinHeight
                //binding.shopName.textSize = 16f
            }
        }
    }


    fun bindView(data: KarnyRadioButtonData, callback: ((Boolean) -> Unit)? = null) {
        this.callback = callback
        binding.shopName.text = data.title
    }

    fun isChecked(isChecked: Boolean) {
        binding.radioButton.isChecked = isChecked
    }


    data class KarnyRadioButtonData(
        var title: String
    )
}
