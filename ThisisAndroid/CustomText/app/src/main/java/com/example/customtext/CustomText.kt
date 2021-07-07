package com.example.customtext

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class CustomText: AppCompatTextView {
    fun process(delimeter: String){
        var one = text.substring(0, 4)
        var two = text.substring(4, 6)
        var three = text.substring(6)
        var settext = "$one $delimeter $two $delimeter $three"
        Log.d("CustomText", settext)
        setText(settext)
    }
    constructor(context: Context):super(context){

    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        val typed = context.obtainStyledAttributes(attrs, R.styleable.CustomText)
        val size = typed.indexCount
        Log.d("CustomText", "here")
        for(i in 0 until size){
            when(typed.getIndex(i)) {
                R.styleable.CustomText_delimeter -> {
                    val delimeter = typed.getString(typed.getIndex(i)) ?: "-"
                    Log.d("CustomText", delimeter)
                    process(delimeter)
                }
            }
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){

    }

}
