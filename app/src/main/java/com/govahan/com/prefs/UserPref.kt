package com.govahan.com.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.govahan.com.model.loginOtpModel.LoginOtpData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPref @Inject constructor(@ApplicationContext context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("userPref", Context.MODE_PRIVATE)
    private val gson: Gson = Gson()
    var user: LoginOtpData
        get() = gson.fromJson<Any>(preferences.getString("user", null), LoginOtpData::class.java) as LoginOtpData
        set(user) {
            val gson = Gson()
            val loginRes = gson.toJson(user)
            preferences.edit().putString("user", loginRes).apply()
        }
    var isLogin: Boolean
        get() = preferences.getBoolean("isLoginA", false)
        set(login) = preferences.edit().putBoolean("isLoginA", login).apply()

    fun clearPref() {
        preferences.edit().clear().apply()
    }


    fun getLocale(): String {
        return preferences.getString("locale", "en")!!
    }

    fun setLocale(locale: String) {
        preferences.edit().putString("locale", locale).apply()
    }

    fun getOTP(): String? {
        return preferences.getString("otp", null)
    }

    fun setOTP(otp: String?) {
        preferences.edit().putString("otp", otp).apply()
    }

    fun setName(name: String) {
        preferences.edit().putString("name", name).apply()
    }

    fun getName(): String? {
        return preferences.getString("name", null)
    }

    fun getToken(): String? {
        return preferences.getString("token", null)
    }
    fun setImage(class_id: String) {
        preferences.edit().putString("image", class_id).apply()
    }

    fun getImage(): String? {
        return preferences.getString("image", null)
    }

    fun setEmail(email: String?) {
        preferences.edit().putString("email", email).apply()
    }

    fun getEmail(): String? {
        return preferences.getString("email", null)
    }

    fun setMobile(mobile: String?) {
        preferences.edit().putString("mobile", mobile).apply()
    }

    fun getmobile(): String? {
        return preferences.getString("mobile", null)
    }

    fun setToken(token: String?) {
        preferences.edit().putString("token", token).apply()
    }

    fun setMentorId(mentor_id: String?) {
        preferences.edit().putString("mentor_id", mentor_id).apply()
    }

    fun getMentorId(): String? {
        return preferences.getString("mentor_id", null)
    }
    //for FCM Token
    fun getFcmToken(): String? {
        return preferences.getString("fcmtoken", null)
    }

    fun setFcmToken(token: String?) {
        preferences.edit().putString("fcmtoken", token).apply()
    }


    fun getSubUserName(): String? {
        return preferences.getString("subUser", null)
    }

    fun setSubUserName(subUser: String?) {
        preferences.edit().putString("subUser", subUser).apply()
    }


    fun getUserId(): String? {
        return preferences.getString("userId", null)
    }

    fun setUserId(userId: String?) {
        preferences.edit().putString("userId", userId).apply()
    }



    fun getDriverId(): String? {
        return preferences.getString("userId", null)
    }

    fun setDriverId(userId: String?) {
        preferences.edit().putString("userId", userId).apply()
    }











    fun getpatientid(): String? {
        return preferences.getString("patient_id", null)
    }

    fun setpatientId(patient_id: String?) {
        preferences.edit().putString("patient_id", patient_id).apply()
    }

    fun getSubscriptionStatus(): String? {
        return preferences.getString("subscription_status", null)
    }

    fun setSubscriptionStatus(subscription_status: String?) {
        preferences.edit().putString("subscription_status", subscription_status).apply()
    }

    fun getAddress(): String? {
        return preferences.getString("address", null)
    }

    fun setAddress(address: String?) {
        preferences.edit().putString("address", address).apply()
    }

    fun getCity(): String? {
        return preferences.getString("city", null)
    }

    fun setCity(city: String?) {
        preferences.edit().putString("city", city).apply()
    }

    fun getState(): String? {
        return preferences.getString("state", null)
    }

    fun setState(state: String?) {
        preferences.edit().putString("state", state).apply()
    }

    fun getPinCode(): String? {
        return preferences.getString("pincode", null)
    }

    fun setpinCode(pincode: String?) {
        preferences.edit().putString("pincode", pincode).apply()
    }


    fun getSubUserId(): String? {
        return preferences.getString("subUserId", null)
    }

    fun setSubUserId(subUserId: String?) {
        preferences.edit().putString("subUserId", subUserId).apply()
    }

    fun getSubtitles(): Boolean {
        return preferences.getBoolean("isSubtitles", false)
    }

    fun setSubtitles(isSubtitles: Boolean) {
        preferences.edit().putBoolean("isSubtitles", isSubtitles).apply()
    }

    fun getDownloadWifi(): Boolean {
        return preferences.getBoolean("isDownloadWifi", false)
    }

    fun setDownloadWifi(isDownloadWifi: Boolean) {
        preferences.edit().putBoolean("isDownloadWifi", isDownloadWifi).apply()
    }

    fun getNotification(): Boolean {
        return preferences.getBoolean("isNotification", true)
    }

    fun setNotification(isNotification: Boolean) {
        preferences.edit().putBoolean("isNotification", isNotification).apply()
    }

    fun getPreferredLanguage(): String {
        return preferences.getString("preferredLanguage", "English")!!
    }

    fun setPreferredLanguage(preferredLanguage: String) {
        preferences.edit().putString("preferredLanguage", preferredLanguage).apply()
    }

    fun getLoginType(): String {
        return preferences.getString("loginType", "1")!!
    }

    fun setLoginType(loginType: String) {
        preferences.edit().putString("loginType", loginType).apply()
    }


    fun getAdult(): Boolean {
        return preferences.getBoolean("isAdult", true)
    }

    fun setAdult(isNotification: Boolean) {
        preferences.edit().putBoolean("isAdult", isNotification).apply()
    }


    fun getBannerImg(): String? {
        return preferences.getString("banner", null)
    }

    fun setBannerImg(banner: String?) {
        preferences.edit().putString("banner", banner).apply()
    }

    fun getUserProfileImage(): String? {
        return preferences.getString("profile_image", null)
    }

    fun setProfileImage(profile_image: String?) {
        preferences.edit().putString("profile_image", profile_image).apply()
    }
















    fun setSmsEmailStatus(smsEmailStatus: String?) {
        preferences.edit().putString("smsEmailStatus", smsEmailStatus).apply()
    }

    fun getSmsEmailStatus(): String? {
        return preferences.getString("smsEmailStatus", null)
    }





    fun setWhatsappStatus(whatsappStatus: String?) {
        preferences.edit().putString("whatsappStatus", whatsappStatus).apply()
    }

    fun getWhatsappStatus(): String? {
        return preferences.getString("whatsappStatus", null)
    }










}