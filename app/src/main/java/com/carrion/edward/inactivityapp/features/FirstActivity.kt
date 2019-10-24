package com.carrion.edward.inactivityapp.features

import android.content.Intent
import android.view.View
import com.carrion.edward.inactivityapp.R
import com.carrion.edward.inactivityapp.base.BaseActivity


class FirstActivity : BaseActivity() {

    fun onClick(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    override fun getLayout(): Int = R.layout.activity_first
}
