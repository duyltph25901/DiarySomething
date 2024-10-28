package com.example.baseprojectflamingo.commons.base.ext

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View

private var lastClickTime: Long = 0

fun View.click(action: (view: View?) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < 300L) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnClickAffect(onClick: ((View?) -> Unit?)? = null) {
    this.setOnTouchListener { v, motionEvent ->
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                v?.alpha = 0.5f
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                v?.alpha = 1f
            }
        }
        false
    }
    this.setOnClickListener {
        onClick?.invoke(it)
    }
}