package com.govahan.com.activities.auth.login

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.govahan.com.R
import com.govahan.com.activities.DashboardActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoginBinding
import com.govahan.com.databinding.DialogOtpBinding
import com.govahan.com.util.toast


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var mGoogleSignInClient : GoogleSignInClient
    private val viewModel : LoginViewModel by viewModels()
    val RC_SIGN_IN = 1
    var languageType="2"
    var referalcode=""
    lateinit var completeprofiletext: String
    var token = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            token = task.result
            Log.d("TAG", "onCreate: "+token)

        })
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        /*binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }*/
        languageType=intent.extras?.getString("language_type").toString()

        binding.btnLogin.setOnClickListener {

            referalcode=binding.referralCode.text.toString()

            if (binding.etMobileNumber.text.toString().trim() == "" || binding.etMobileNumber.text.toString().trim().length <  10){
                toast("Please enter your valid mobile phone number")
            }
            else {
                viewModel.userLogin(binding.etMobileNumber.text.toString().trim(),"Android","Android","Android",token)
            }
        }

        viewModel.userLoginResponse.observe(this) {
            Log.d("Response", it.toString())
            if (it.status == 1) {
//                toast(it.data!!.otp.toString())
                otpDialog(it.data!!.otp.toString(), binding.etMobileNumber.text.toString().trim())
            } else {
                toast(it.message.toString())
            }
        }


        viewModel.progressBarStatus.observe(this, Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })

    }


    private fun otpDialog(otpResponse : String, mobile : String) {
        val cDialogOtp = Dialog(this, R.style.Theme_Tasker_Dialog)
        val dialogOtpBinding: DialogOtpBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_otp,null, false)
        cDialogOtp.setContentView(dialogOtpBinding.root)
        cDialogOtp.setCancelable(false)
        cDialogOtp.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        cDialogOtp.show()
        dialogOtpBinding.tvResend.setOnClickListener(View.OnClickListener {
//            cDialogOtp.dismiss()
            dialogOtpBinding.otpView.setText("")
            viewModel.userLogin(binding.etMobileNumber.text.toString().trim(),"Android","Android","Android","Android")
        })
        viewModel.userOtpVerificationResponse.observe(this, Observer {
            if (it.status == 1){
                Toast.makeText(this, "Your Account is Registered Successfully", Toast.LENGTH_LONG).show()
                userPref.user = it.loginResponseData!!
                userPref.isLogin = true
                userPref.setToken(it.loginResponseData!!.apiToken)
                it.loginResponseData!!.name?.let { it1 -> userPref.setSubUserName(it1) }
                userPref.setEmail(it.loginResponseData!!.email)
                userPref.setMobile(it.loginResponseData!!.mobileNumber)
                userPref.setProfileImage(it.loginResponseData!!.profileImage.toString())

                userPref.setSmsEmailStatus(it.loginResponseData!!.smsEmail)
                userPref.setWhatsappStatus(it.loginResponseData!!.whatsappStatus)

                //   val complete_profile = it.loginResponseData!!.completeProfile.toString()

                if(it.loginResponseData!!.completeProfile.toString().equals("0")){
                    completeprofiletext = "0"
                }
                else if(it.loginResponseData!!.completeProfile.toString().equals("1")){
                    completeprofiletext = "1"
                }

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("complete_profile", completeprofiletext)
                startActivity(intent)
                Log.d(TAG, "otpDialog: token___"+it.loginResponseData!!.apiToken)
            }
            else {
                it.message?.let { it1 -> toast(it1) }
            }
        } )
        dialogOtpBinding.btnSubmit.setOnClickListener {
            Log.e("@@otpView", dialogOtpBinding.otpView.text.toString() + "D")
            when {
                dialogOtpBinding.otpView.text.toString().isEmpty() -> {
                    toast("Please enter OTP.")
                }
                otpResponse == dialogOtpBinding.otpView.text.toString() -> {
                    viewModel.userOtpVerification(otpResponse, mobile,referalcode)
                    cDialogOtp.dismiss()
                }
                else -> {
                    toast("Enter valid OTP.")
                }
            }
        }
        dialogOtpBinding.btnCancel.setOnClickListener {
            cDialogOtp.dismiss()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
                toast(account.email!!)
                Log.d("UserEmail", account.email.toString())
                Log.d("TAG", "GoogleId: ${account.id}")

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("TAG", "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
        }
    }
}