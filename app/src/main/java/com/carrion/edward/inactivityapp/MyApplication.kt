package com.carrion.edward.inactivityapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.carrion.edward.inactivityapp.base.BaseActivity

class MyApplication : Application(), Application.ActivityLifecycleCallbacks {
    private lateinit var activity: Activity
    private lateinit var expireSessionRunnable: Runnable
    private var inactivityHandler = Handler()

    override fun onCreate() {
        super.onCreate()

        expireSessionRunnable = Runnable {
            Log.i(TAG, "MyApplication > Finish Session")
            val baseActivity = activity as BaseActivity
            baseActivity.showMessageSession()
        }
    }

    override fun onActivityPaused(activity: Activity) {
        Log.i(TAG, "MyApplication > onActivityPaused ${activity::class.java.simpleName}")

        this.activity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        Log.i(TAG, "MyApplication > onActivityStarted ${activity::class.java.simpleName}")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.i(TAG, "MyApplication > onActivityDestroyed ${activity::class.java.simpleName}")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.i(TAG, "MyApplication > onActivitySaveInstanceState ${activity::class.java.simpleName}")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.i(TAG, "MyApplication > onActivityStopped ${activity::class.java.simpleName}")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.i(TAG, "MyApplication > onActivityCreated ${activity::class.java.simpleName}")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.i(TAG, "MyApplication > onActivityResumed ${activity::class.java.simpleName}")

        this.activity = activity

        if (activity is BaseActivity && activity.shouldExpireSession()) {
            startInactivityTimer()
        } else {
            cancelTimers()
        }
    }

    private fun startInactivityTimer() {
        inactivityHandler.removeCallbacks(expireSessionRunnable)
        inactivityHandler.postDelayed(expireSessionRunnable, TIMEOUT_INACTIVITY)
    }

    private fun cancelTimers() {
        inactivityHandler.removeCallbacks(expireSessionRunnable)
    }

    companion object {
        const val TAG = "MyApplication"
        const val TIMEOUT_INACTIVITY = 10000L
    }
}