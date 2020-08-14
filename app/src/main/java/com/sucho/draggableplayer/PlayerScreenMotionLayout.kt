package com.sucho.draggableplayer

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

class PlayerScreenMotionLayout(
  context: Context,
  attributeSet: AttributeSet? = null
) : MotionLayout(context, attributeSet) {

  private val viewToDetectTouch by lazy {
    findViewById<View>(R.id.player_background_view)
  }
  private val viewRect = Rect()
  private var hasTouchStarted = false
  private val transitionListenerList = mutableListOf<TransitionListener?>()

  init {
    addTransitionListener(object : MotionLayout.TransitionListener {
      override fun onTransitionTrigger(
        p0: MotionLayout?,
        p1: Int,
        p2: Boolean,
        p3: Float
      ) {
      }

      override fun onTransitionStarted(
        p0: MotionLayout?,
        p1: Int,
        p2: Int
      ) {
      }

      override fun onTransitionChange(
        p0: MotionLayout?,
        p1: Int,
        p2: Int,
        p3: Float
      ) {
      }

      override fun onTransitionCompleted(
        p0: MotionLayout?,
        p1: Int
      ) {
        hasTouchStarted = false
      }
    })

    super.setTransitionListener(object : MotionLayout.TransitionListener {
      override fun onTransitionTrigger(
        p0: MotionLayout?,
        p1: Int,
        p2: Boolean,
        p3: Float
      ) {
      }

      override fun onTransitionStarted(
        p0: MotionLayout?,
        p1: Int,
        p2: Int
      ) {
      }

      override fun onTransitionChange(
        p0: MotionLayout?,
        p1: Int,
        p2: Int,
        p3: Float
      ) {
        transitionListenerList.filterNotNull()
            .forEach { it.onTransitionChange(p0, p1, p2, p3) }
      }

      override fun onTransitionCompleted(
        p0: MotionLayout?,
        p1: Int
      ) {
        transitionListenerList.filterNotNull()
            .forEach { it.onTransitionCompleted(p0, p1) }
      }
    })
  }

  override fun setTransitionListener(listener: TransitionListener?) {
    addTransitionListener(listener)
  }

  override fun addTransitionListener(listener: TransitionListener?) {
    transitionListenerList += listener
  }

  private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
      transitionToEnd()
      return false
    }
  })

  override fun onTouchEvent(event: MotionEvent): Boolean {
    //gestureDetector.onTouchEvent(event)
    when (event.actionMasked) {
      MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
        hasTouchStarted = false
        return super.onTouchEvent(event)
      }
    }
    if (!hasTouchStarted) {
      viewToDetectTouch.getHitRect(viewRect)
      hasTouchStarted = viewRect.contains(event.x.toInt(), event.y.toInt())
    }
    return hasTouchStarted && super.onTouchEvent(event)
  }
}