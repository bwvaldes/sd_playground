package com.scubadeving.sd_playground.ui

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color.WHITE
import android.graphics.Typeface
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat
import com.google.android.material.card.MaterialCardView
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.view_infoview.view.*

class InfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_infoview, this)

        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(
                    it,
                    R.styleable.InfoView,
                    R.attr.infoViewStyle,
                    R.style.Widget_AppTheme_InfoView
            )

            configureInfoViewIcon(styledAttributes, context)
            configureInfoViewHeading(styledAttributes)
            configureInfoViewData(styledAttributes)

            styledAttributes.recycle()
        }
    }

    private fun configureInfoViewIcon(styledAttributes: TypedArray, context: Context) {
        val icon = styledAttributes.getDrawable(R.styleable.InfoView_icon)
        val iconSize =
            styledAttributes.getDimension(R.styleable.InfoView_iconSize, DEFAULT_ICON_SIZE)
        val iconTintColorResource =
            styledAttributes.getResourceId(R.styleable.InfoView_iconTint, android.R.color.white)
        val iconTintColor = ContextCompat.getColor(context, iconTintColorResource)

        buildInfoViewIcon(icon, iconSize, iconTintColor)
    }

    private fun buildInfoViewIcon(icon: Drawable?, iconSize: Float, iconColor: Int) {
        setIcon(icon)
        setIconSize(iconSize)
        setIconColor(iconColor)
    }

    private fun setIcon(icon: Drawable?) {
        info_view_icon.setImageDrawable(icon)
    }

    private fun setIconSize(dimensions: Float) {
        val dimensionInDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dimensions,
            resources.displayMetrics
        ).toInt()
        info_view_icon.apply {
            layoutParams.height = dimensionInDp
            layoutParams.width = dimensionInDp
            requestLayout()
        }
    }

    private fun setIconColor(@ColorInt color: Int) {
        ImageViewCompat.setImageTintList(info_view_icon, ColorStateList.valueOf(color))
    }

    private fun configureInfoViewHeading(styledAttributes: TypedArray) {
        val headingText = styledAttributes.getString(R.styleable.InfoView_headingText)
        val headingTextSize = styledAttributes.getDimensionPixelSize(
            R.styleable.InfoView_headingTextSize,
            DEFAULT_TEXT_SIZE_HEADING
        )
        val headingTextColor =
            styledAttributes.getColor(R.styleable.InfoView_headingTextColor, DEFAULT_TEXT_COLOR)
        val headingTextStyle =
            styledAttributes.getInt(R.styleable.InfoView_headingTextStyle, DEFAULT_TEXT_STYLE)
        val headingStyleRes = styledAttributes.getResourceId(
            R.styleable.InfoView_headingTextAppearance,
            R.style.TextAppearance_MaterialComponents_Headline1
        )
        TextViewCompat.setTextAppearance(info_view_heading_text, headingStyleRes)

        buildInfoViewHeading(
            headingText,
            headingTextSize,
            headingTextColor,
            headingTextStyle
        )
    }

    private fun buildInfoViewHeading(
            text: String?,
            textSize: Int,
            textColor: Int,
            textStyle: Int
    ) {
        setHeadingText(text)
        setHeadingTextSize(textSize)
        setHeadingTextColor(textColor)
        setHeadingTextStyle(textStyle)
    }

    private fun setHeadingText(text: String?) {
        info_view_heading_text.text = text
    }

    private fun setHeadingTextSize(textSize: Int) {
        info_view_heading_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    private fun setHeadingTextColor(@ColorInt color: Int) {
        info_view_heading_text.setTextColor(color)
    }

    private fun setHeadingTextStyle(textStyle: Int) {
        info_view_heading_text.typeface = Typeface.defaultFromStyle(textStyle)
    }

    private fun configureInfoViewData(styledAttributes: TypedArray) {
        val dataText = styledAttributes.getString(R.styleable.InfoView_dataText)
        val dataTextSize = styledAttributes.getDimensionPixelSize(
            R.styleable.InfoView_dataTextSize,
            DEFAULT_TEXT_SIZE_DATA
        )
        val dataTextColor = styledAttributes.getColor(
            R.styleable.InfoView_dataTextColor,
            DEFAULT_TEXT_COLOR
        )
        val dataTextStyle =
            styledAttributes.getInt(R.styleable.InfoView_dataTextStyle, DEFAULT_TEXT_STYLE)
        val dataStyleRes = styledAttributes.getResourceId(
            R.styleable.InfoView_dataTextAppearance,
            R.style.TextAppearance_MaterialComponents_Body1
        )
        TextViewCompat.setTextAppearance(info_view_data_text, dataStyleRes)

        buildInfoViewData(
            dataText,
            dataTextSize,
            dataTextColor,
            dataTextStyle
        )
    }

    private fun buildInfoViewData(
            text: String?,
            textSize: Int,
            textColor: Int,
            textStyle: Int
    ) {
        setDataText(text)
        setDataTextSize(textSize)
        setDataTextColor(textColor)
        setDataTextStyle(textStyle)
    }

    private fun setDataText(text: String?) {
        info_view_data_text.text = text
    }

    private fun setDataTextSize(textSize: Int) {
        info_view_data_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    private fun setDataTextColor(@ColorInt color: Int) {
        info_view_data_text.setTextColor(color)
    }

    private fun setDataTextStyle(textStyle: Int) {
        info_view_data_text.typeface = Typeface.defaultFromStyle(textStyle)
    }

    companion object {
        private const val DEFAULT_TEXT_SIZE_HEADING = 36
        private const val DEFAULT_TEXT_SIZE_DATA = 24
        private const val DEFAULT_TEXT_COLOR = WHITE
        private const val DEFAULT_TEXT_STYLE = NORMAL
        private const val DEFAULT_ICON_SIZE = 24f
    }
}