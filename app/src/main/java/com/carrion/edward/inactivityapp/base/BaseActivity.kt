package com.carrion.edward.inactivityapp.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.carrion.edward.inactivityapp.MyApplication
import com.carrion.edward.inactivityapp.R
import com.carrion.edward.inactivityapp.login.LoginActivity

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var myApplication: MyApplication

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        myApplication = application as MyApplication
        myApplication.onActivityCreated(this, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        myApplication.onActivityPaused(this)
    }

    override fun onStart() {
        super.onStart()
        myApplication.onActivityStarted(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        myApplication.onActivityDestroyed(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        myApplication.onActivitySaveInstanceState(this, outState)
    }

    override fun onStop() {
        super.onStop()
        myApplication.onActivityStopped(this)
    }

    override fun onResume() {
        super.onResume()
        myApplication.onActivityResumed(this)
    }

    open fun shouldExpireSession() : Boolean = true

    fun showMessageSession() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage(getString(R.string.session_message))
        builder.setPositiveButton(getString(R.string.accept)) { _, _ ->
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val dialog = builder.create()
        dialog.show()

    }
}