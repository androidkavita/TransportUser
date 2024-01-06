package com.govahan.com.util

import android.util.Patterns
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

//    fun setFragment(fragment: Fragment, removeStack: Boolean, activity: FragmentActivity, mContainer: Int) {
//        val fragmentManager = activity.supportFragmentManager
//        val ftTransaction = fragmentManager.beginTransaction()
//        if (removeStack) {
//            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            ftTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
//            ftTransaction.replace(mContainer, fragment)
//            //ftTransaction.addToBackStack(null)
//        } else {
//            ftTransaction.replace(mContainer, fragment)
//            //ftTransaction.addToBackStack(null)
//        }
//        ftTransaction.commit()
//    }

    fun addFragment(fragment: Fragment,activity: FragmentActivity, mContainer: Int){
        val fragmentManager = activity.supportFragmentManager
        val ftTransaction = fragmentManager.beginTransaction()
        ftTransaction.add(mContainer,fragment)
        ftTransaction.commit()
    }

    fun getNumberOfFragmentInContainer(activity: FragmentActivity) : Int{
        val fragmentManager = activity.supportFragmentManager
        return fragmentManager.backStackEntryCount
    }

 /*   fun getCurrentFragment(activity: FragmentActivity) : Fragment?{
        val fragmentManager = activity.supportFragmentManager
        return fragmentManager.findFragmentById(R.id.frame_Container)
    }*/

    /*To convert string date from one format to another format*/
    fun getDate(currentFormat: String, requiredFormat: String, dateString: String): String? {
        var result = ""
        if (dateString.isNullOrEmpty()) {
            return result
        }
        val formatterOld =
            SimpleDateFormat(currentFormat, Locale.getDefault())
        val formatterNew =
            SimpleDateFormat(requiredFormat, Locale.getDefault())
        var date: Date? = null
        try {
            date = formatterOld.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            result = formatterNew.format(date)
        }
        return result
    }

    fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordConfirmPasswordSame(password : String,confirmPassword : String): Boolean {
        return password.equals(confirmPassword)
    }

}