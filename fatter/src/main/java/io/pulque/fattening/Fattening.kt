package io.pulque.fattening

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Scroller
import androidx.core.content.ContextCompat
import com.wizeline.fattening.R

private const val MAX_LINES = 5

class Fattening @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet
) : RelativeLayout(context, attributeSet){

    var onTextCreated : ((String) -> Unit) = {}

    init {
        inflate(context, R.layout.fattering, this)

        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fattening)
        val buttonText = attributes.getString(R.styleable.Fattening_buttonText)
        val buttonTextColor = attributes.getColor(R.styleable.Fattening_buttonTextColor, ContextCompat.getColor(context,R.color.buttonTextColorDefault))
        val maxLinesInput = attributes.getInteger(R.styleable.Fattening_maxLines, MAX_LINES)
        attributes.recycle()

        val input = findViewById<EditText>(R.id.input).apply {
            setScroller(Scroller(context))
            isVerticalScrollBarEnabled = true
            movementMethod = ScrollingMovementMethod()
            maxLines = maxLinesInput
        }

        findViewById<Button>(R.id.create).apply {
            text = buttonText
            setTextColor(buttonTextColor)
            setOnClickListener {
                onTextCreated(input.text.toString())
            }
        }
    }
}
