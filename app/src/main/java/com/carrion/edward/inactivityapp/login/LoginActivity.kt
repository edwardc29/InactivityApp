package com.carrion.edward.inactivityapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.carrion.edward.inactivityapp.R
import com.carrion.edward.inactivityapp.base.BaseActivity
import com.carrion.edward.inactivityapp.features.FirstActivity

class LoginActivity : BaseActivity() {

    fun onClick(view: View) {
        val intent = Intent(this, FirstActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun getLayout(): Int = R.layout.activity_login
}
