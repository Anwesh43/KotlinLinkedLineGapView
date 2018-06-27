package com.anwesh.uiprojects.linkedlinegapview

/**
 * Created by anweshmishra on 27/06/18.
 */

import android.graphics.Canvas
import android.graphics.Paint
import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.View

val LG_NODES = 5

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

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(stopcb : (Float) -> Unit) {
            scale += 0.1f * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(prevScale)
            }
        }

        fun startUpdating(startcb : () -> Unit) {
            if (dir == 0f) {
                dir = 1 - 2 * prevScale
                startcb()
            }
        }
    }

    data class Animator(var view : View, var animated : Boolean = false) {

        fun animate(cb : () -> Unit) {
            if (animated) {
                cb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                } catch(ex : Exception) {

                }
            }
        }

        fun start() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }

        fun stop() {
            if (animated) {
                animated = false
            }
        }
    }

    data class LGNode(var i : Int) {

        private val state : State = State()

        private var next : LGNode? = null

        private var prev : LGNode? = null

        init {
            addNeighbor()
        }

        fun addNeighbor() {
            if (i < LG_NODES - 1) {
                next = LGNode(i + 1)
                next?.prev = this
            }
        }

        fun draw(canvas : Canvas, paint : Paint) {
            val w : Float = canvas.width.toFloat()
            val h : Float = canvas.height.toFloat()
            val size : Float = Math.min(w, h) / 10
            val wgap : Float = w / LG_NODES
            val hgap : Float = h / LG_NODES
            paint.color = Color.parseColor("#673AB7")
            paint.strokeWidth = Math.min(w, h) / 60
            paint.strokeCap = Paint.Cap.ROUND
            canvas.save()
            canvas.translate(i * wgap + wgap * state.scale, h/2)
            for (i in 0..1) {
                val gap = wgap * i + wgap * state.scale
                canvas.save()
                canvas.drawLine(0f, gap * (1 - 2 * i), size, gap * (1 - 2 * i), paint)
                canvas.restore()
            }
            canvas.restore()
        }

        fun update(stopcb : (Float) -> Unit) {
            state.update(stopcb)
        }

        fun startUpdating(startcb : () -> Unit) {
            state.startUpdating(startcb)
        }

        fun getNext(dir : Int, cb : () -> Unit) : LGNode {
            var curr : LGNode? = prev
            if (dir == 1) {
                curr = next
            }
            if (curr != null) {
                return curr
            }
            cb()
            return this
        }
    }
}
