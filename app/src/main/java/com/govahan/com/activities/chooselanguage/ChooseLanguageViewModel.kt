package com.govahan.com.activities.chooselanguage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.govahan.com.prefs.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/*private val mainRepository: MainRepository*/
@HiltViewModel
class ChooseLanguageViewModel @Inject constructor(val userPref: UserPref) :
    ViewModel() {

    var selected=MutableLiveData<String>()
    var error = MutableLiveData<Int>()
    var errorString = MutableLiveData<String>()
    val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility


    fun onLanguageClicked(type:Int) {
        if(type==1)
        {
            selected.value="1"
        }
        else if(type==2)
        {
            selected.value="2"
        }

        userPref.setPreferredLanguage(selected.value.toString())
    }

}
