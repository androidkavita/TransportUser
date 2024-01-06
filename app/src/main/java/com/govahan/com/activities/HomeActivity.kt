package com.govahan.com.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.govahan.com.R
import com.govahan.com.activities.auth.login.LoginActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityHomeBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity() ,  NavigationView.OnNavigationItemSelectedListener {
    /*private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }*/
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
//        Log.d("AuthToken" , userPref.user.api_token)

       /* binding.navHea.menu.findItem(R.id.logout).setOnMenuItemClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            showLogoutDialog()
            true
        }*/

      //  NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);

  //      NavigationUI.setupWithNavController(binding.navView, navController);

    //    binding.navView.setNavigationItemSelectedListener(this)

      //  val headerView: View = binding.navView.getHeaderView(0)
       /* val tvEmail : TextView = headerView.findViewById(R.id.tvEmail)
        val tvNumber : TextView = headerView.findViewById(R.id.tvNumber)
        val ivUser : ImageView = headerView.findViewById(R.id.ivUser)*/


       /* tvNumber.text = userPref.user.mobile_number
        tvEmail.text = userPref.user.email*/

        /*Glide.with(this).load(userPref.user.profile_image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_person))
            .apply(RequestOptions.errorOf(R.drawable.ic_person))
            .into(ivUser)*/
    }



    /*override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }*/

//    private fun setupDrawerLayout() {
//        binding.navView.setupWithNavController(navController)
////        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
//    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Are you sure you want to logout ")


        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()
            userPref.clearPref()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }


        //performing negative action
        builder.setNegativeButton("No", null)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()


    }
    /*private fun prepareNavMenuList() {

        menuList!!.clear()
        menuList!!.add(DashboardMenuModel(this.getString(R.string.myprofile),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.bookinghistory),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.favouritelocations),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.notifications),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.invoices),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.paymentoptions),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.myoffers),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.contactus),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.aboutus),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.rateapp),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.privacypolicy),R.drawable.ic_arrow_back))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.logout),R.drawable.ic_arrow_back))


        menuListAdapter = MenuListAdapter(this, menuList!!)
        //  binding.listVideMenu.adapter = menuListAdapter
    }*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        binding.drawerLayout.closeDrawers()

        when (item.itemId) {
          //  R.id.newBookingFragment -> navController.navigate(R.id.newBookingFragment)
         //  R.id.profileFragment -> navController.navigate(R.id.profileFragment)
           // R.id.bookingHistoryFragment -> navController.navigate(R.id.bookingHistoryFragment)
          //  R.id.transanctionReportFragment -> navController.navigate(R.id.transanctionReportFragment)
          //  R.id.notificationFragment -> navController.navigate(R.id.notificationFragment)
          //  R.id.offersFragment -> navController.navigate(R.id.offersFragment)
          //  R.id.rewardsAndEarnFragment -> navController.navigate(R.id.rewardsAndEarnFragment)
          //  R.id.contactUsFragment -> navController.navigate(R.id.contactUsFragment)
          //  R.id.aboutUsFragment -> navController.navigate(R.id.aboutUsFragment)
          //  R.id.privacyPolicyFragment -> navController.navigate(R.id.privacyPolicyFragment)
          //  R.id.termsAndConditionsFragment -> navController.navigate(R.id.termsAndConditionsFragment)
        }
        return true

    }
}