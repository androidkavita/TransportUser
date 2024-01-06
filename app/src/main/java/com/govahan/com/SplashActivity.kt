package com.govahan.com

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.govahan.com.activities.DashboardActivity
import com.govahan.com.activities.chooselanguage.ChooseLanguageActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivitySplashBinding
import com.govahan.com.prefs.UserPref
import kotlinx.coroutines.*

class SplashActivity : BaseActivity() {

    var token = ""
    val activityScope = CoroutineScope(Dispatchers.Main)
    private val SPLASH_TIMEOUT: Long = 3000
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        userPref = UserPref(this)
        getDeviceToken()
       // getDeviceToken()

        /*activityScope.launch {
            delay(3000)
            if(userPref.isLogin){
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this@SplashActivity, ChooseLanguageActivity::class.java)
                startActivity(intent)
                finish()
            }
        }*/

        moveForword()


    }

    private fun moveForword(){
        changeLanguage(userPref.getLocale())

        Handler().postDelayed({
            if (userPref.isLogin) {
                Log.e("@@token",userPref.getFcmToken().toString())

                Intent(this, DashboardActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            else {
                startActivity(Intent(this, ChooseLanguageActivity::class.java))
                finish()
            }
        }, SPLASH_TIMEOUT)
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    private fun getDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("deviceToken", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            Log.w("deviceToken", task.result, task.exception)
//            userPref.firebaseToken = task.result

            Log.w("deviceTokenInUserPref", token , task.exception)
        })
    }







}