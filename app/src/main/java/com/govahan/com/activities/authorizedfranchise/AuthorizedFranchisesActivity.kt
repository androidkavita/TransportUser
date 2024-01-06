package com.govahan.com.activities.authorizedfranchise

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.adapters.AuthorizedFranchiseAdapter
import com.govahan.com.adapters.SearchAuthorizedFranchiseAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.customclick.franchiseclick
import com.govahan.com.databinding.ActivityAuthorizedFranchisesBinding
import com.govahan.com.model.authorisedFranchisesDisttListModel.FranchisesDisttData
import com.govahan.com.model.authorisedFranchisesPincodeListModel.FranchisesPinCodeData
import com.govahan.com.model.authorisedFranchisesStateListModel.FranchisesStateData
import com.govahan.com.model.authorizedfranchisesmodel.AuthorizedFranchisesData
import com.govahan.com.model.searchauthorisedfranchisesmodel.SearchAuthorisedFranchisesData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AuthorizedFranchisesActivity : BaseActivity(), AuthorizedFranchiseAdapter.OnClick,
    franchiseclick {
    private lateinit var binding : ActivityAuthorizedFranchisesBinding
    private val viewModel : AuthorizedFranchisesViewModel by viewModels()
    private var authorizedFranchiseAdapter : AuthorizedFranchiseAdapter?= null
    private var searchAuthorizedFranchiseAdapter : SearchAuthorizedFranchiseAdapter?= null

    private var listData: ArrayList<AuthorizedFranchisesData> = ArrayList()
    private var listSearchAuthorisedFranchises: ArrayList<SearchAuthorisedFranchisesData> = ArrayList()

    private var franchisesStateListData = ArrayList<FranchisesStateData>()
    var selectedFranchisesState = ""
    private var franchisesStateList = ArrayList<String>()

    private var franchisesDisttListData = ArrayList<FranchisesDisttData>()
    var selectedFranchisesDistt = ""
    private var franchisesDisttList = ArrayList<String>()

    private var franchisesPinCodeListData = ArrayList<FranchisesPinCodeData>()
    var selectedFranchisesPinCode = ""
    private var franchisesPinCodeList = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorized_franchises)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Authorized Franchises")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.searchAuthorisedFranchisesResponseModel.observe(this) {
            if (it.status == 1) {
                listData.clear()
                listSearchAuthorisedFranchises.clear()
                if (it.searchfranchisedata.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.llRvlistAll.visibility = View.GONE
                    binding.llRvlistSearch.visibility = View.GONE
                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.llRvlistAll.visibility = View.GONE
                    binding.llRvlistSearch.visibility = View.VISIBLE
                    listSearchAuthorisedFranchises.addAll(it.searchfranchisedata)
                    searchAuthorizedFranchiseAdapter = SearchAuthorizedFranchiseAdapter(listSearchAuthorisedFranchises, this,this)
                    binding.rvSearchAuthorizedFranchise.apply {
                        adapter = searchAuthorizedFranchiseAdapter
                        layoutManager = LinearLayoutManager(this@AuthorizedFranchisesActivity)
                    }
                    searchAuthorizedFranchiseAdapter!!.notifyDataSetChanged()
                }
            } else {
                Log.d("Response", it.toString())
                toast(this,it.message!!)
                binding.idNouser.visibility = View.VISIBLE
                binding.llRvlistAll.visibility = View.GONE
                binding.llRvlistSearch.visibility = View.GONE
            }
        }
        binding.btnSearch.setOnClickListener(View.OnClickListener {
            listData.clear()
            viewModel.searchAuthorizedFranchisesApi("Bearer " +
                    userPref.user.apiToken, selectedFranchisesState,
                selectedFranchisesDistt,binding.pincode.text.toString())
        })

        viewModel.getAuthorizedFranchisesResponseModel.observe(this) {
            if (it.status == 1) {
                listData.clear()
                listSearchAuthorisedFranchises.clear()
                if (it.franchisealldata.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.llRvlistAll.visibility = View.GONE
                    binding.llRvlistSearch.visibility = View.GONE
                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.llRvlistAll.visibility = View.VISIBLE
                    binding.llRvlistSearch.visibility = View.GONE
                    listData.addAll(it.franchisealldata)
                    authorizedFranchiseAdapter = AuthorizedFranchiseAdapter(this,listData, this,this)
                    binding.rvAuthorizedFranchise.apply {
                        adapter = authorizedFranchiseAdapter
                        layoutManager = LinearLayoutManager(this@AuthorizedFranchisesActivity)
                    }
                    authorizedFranchiseAdapter!!.notifyDataSetChanged()
                }
            } else   {
                Log.d("Response", it.toString())
                toast(this,it.message!!)
                binding.idNouser.visibility = View.VISIBLE
                binding.llRvlistAll.visibility = View.GONE
                binding.llRvlistSearch.visibility = View.GONE
            }
        }

    viewModel.getAuthorizedFranchisesApi("Bearer " + userPref.user.apiToken)

        //  GetStateList
        viewModel.getAuthorisedFranchisesStateListApi("Bearer " + userPref.user.apiToken)
        binding.spinnerSelectState.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            selectedFranchisesState = franchisesStateListData[newIndex].id.toString()


            viewModel.getAuthorisedFranchisesDisttListApi("Bearer " + userPref.user.apiToken,selectedFranchisesState.toString() )

            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }
        viewModel.franchisesStateListResponse.observe(this) {
            if (it.status == 1) {
                franchisesStateList.clear()
                franchisesStateListData.clear()
                franchisesStateListData.addAll(it.data)

                for (i in franchisesStateListData){
                    i.stateName?.let { it1 -> franchisesStateList.add(it1) }
                }

                binding.spinnerSelectState.setItems(franchisesStateList)
            } else {
                it.message?.let { it1 -> toast(this, it1) }
            }
        }

        //  GetDisttList
        binding.spinnerSelectDistrict.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            toast(this, franchisesDisttListData[newIndex].id.toString())
            selectedFranchisesDistt = franchisesDisttListData[newIndex].id.toString()

            viewModel.getAuthorisedFranchisesPincodeListApi("Bearer " + userPref.user.apiToken,selectedFranchisesDistt.toString() )

            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }
        viewModel.franchisesDisttListResponse.observe(this) {
            if (it.status == 1) {
                franchisesDisttList.clear()
                franchisesDisttListData.clear()
                franchisesDisttListData.addAll(it.data)

                for (i in franchisesDisttListData){
                    i.name?.let { it1 -> franchisesDisttList.add(it1.toString()) }
                }

                binding.spinnerSelectDistrict.setItems(franchisesDisttList)
            } else {
                it.message?.let { it1 -> toast(this, it1) }
            }
        }

        //  GetPincodeList
//        binding.spinnerSelectPin.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
////            toast(this, franchisesPinCodeListData[newIndex].id.toString())
//            selectedFranchisesPinCode = franchisesPinCodeListData[newIndex].id.toString()
//            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
//        }
//        viewModel.franchisesPincodeListResponse.observe(this) {
//            if (it.status == 1) {
//                franchisesPinCodeList.clear()
//                franchisesPinCodeListData.clear()
//                franchisesPinCodeListData.addAll(it.data)
//
//                for (i in franchisesPinCodeListData){
//                    i.pincode?.let { it1 -> franchisesPinCodeList.add(it1.toString()) }
//                }
//
//                binding.spinnerSelectPin.setItems(franchisesPinCodeList)
//            } else {
//                it.message?.let { it1 -> toast(this, it1) }
//            }
//        }










    }


    override fun onCallNowClicked(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    override fun franchiseclick(id: String) {
        val intent = Intent(this, AuthorizedFranchisesDetail::class.java).putExtra("id",id)
        startActivity(intent)
    }


    /* override fun onResume() {
         super.onResume()
    viewModel.getAuthorizedFranchisesApi("Bearer " + userPref.user.apiToken)

     }*/






}