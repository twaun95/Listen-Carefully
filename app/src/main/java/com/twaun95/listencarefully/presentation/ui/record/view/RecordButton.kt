package com.twaun95.listencarefully.presentation.ui.record.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.presentation.ui.record.State

class RecordButton(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        background = ContextCompat.getDrawable(context, R.drawable.shape_oval_button)
    }

    fun update(state: State){
        background = when(state) {
            State.IDLE -> ContextCompat.getDrawable(context, R.drawable.record)
            State.ON_RECORD -> ContextCompat.getDrawable(context, R.drawable.record_stop)
            State.READY_PLAY -> ContextCompat.getDrawable(context, R.drawable.play)
            State.ON_PLAY -> ContextCompat.getDrawable(context, R.drawable.pause)
        }
    }
}