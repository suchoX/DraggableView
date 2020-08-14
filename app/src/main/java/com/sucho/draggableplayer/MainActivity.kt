package com.sucho.draggableplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

  private var playScreenFragment = PlayScreenFragment.newInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupPlayScreenFragment()
  }

  private fun setupPlayScreenFragment() {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.play_screen_frame_layout, playScreenFragment, PlayScreenFragment.TAG)
        .commitAllowingStateLoss()
  }
}