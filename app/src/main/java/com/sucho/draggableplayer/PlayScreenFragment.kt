package com.sucho.draggableplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sucho.draggableplayer.databinding.FragmentPlayScreenBinding

class PlayScreenFragment: Fragment() {
  companion object {
    const val TAG = "PlayScreenFragment"
    fun newInstance(): PlayScreenFragment {
      val args = Bundle()
      val playScreenFragment = PlayScreenFragment()
      playScreenFragment.arguments = args
      return playScreenFragment
    }
  }

  lateinit var fragmentPlayScreenBinding: FragmentPlayScreenBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    fragmentPlayScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_screen, container, false)
    return fragmentPlayScreenBinding.root
  }
}