package com.anwesh.uiprojects.linkedlinegapview

/**
 * Created by anweshmishra on 27/06/18.
 */

import android.graphics.Canvas
import android.graphics.Paint
import android.content.Context
import android.view.MotionEvent
import android.view.View

class LinkedLineGapView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                
            }
        }
        return true
    }
}
