package com.govahan.com.activities


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView
import com.govahan.com.R
import com.govahan.com.activities.aboutus.AboutUsActivity
import com.govahan.com.activities.cancellation.CancellationPolicy
import com.govahan.com.activities.chooselanguage.ChooseLanguageActivity
import com.govahan.com.activities.contactus.ContactUsActivity
import com.govahan.com.activities.favouritelocations.AddFavouriteLocationsActivity
import com.govahan.com.activities.myoffers.MyOffersActivity
import com.govahan.com.activities.myprofile.MyProfileActivity
import com.govahan.com.activities.notification.NotificationActivity
import com.govahan.com.activities.privacypolicy.PrivacyPolicyActivity
import com.govahan.com.activities.referearn.ReferEarnActivity
import com.govahan.com.activities.settings.SettingsActivity
import com.govahan.com.activities.termsconditions.TermsConditionsActivity
import com.govahan.com.activities.wallet.LoaderWalletActivity
import com.govahan.com.adapters.MenuListAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityDashboardBinding
import com.govahan.com.databinding.DialogCompleteProfileBinding
import com.govahan.com.databinding.DialogLogoutBinding
import com.govahan.com.fragment.home.HomeFragment
import com.govahan.com.model.DashboardMenuModel
import com.govahan.com.prefs.UserPref
import com.govahan.com.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
@AndroidEntryPoint
class DashboardActivity : BaseActivity(),  NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityDashboardBinding
    private var menuList: ArrayList<DashboardMenuModel>?  = null
    private var menuListAdapter: MenuListAdapter? =  null
    private var exit = false
    private lateinit var drawerLayout : DrawerLayout
    private var drawerToggle: ActionBarDrawerToggle? = null
    lateinit var complete_profilee: String
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStart() {
        super.onStart()
        //binding.bottomNav.menu.clear()
        setNavigationData()
       // setNavigationBar()
       // initializeUsersBnv()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        userPref = UserPref(this)
        utils = Utils(this)
        menuList = ArrayList<DashboardMenuModel>()

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }
            override fun onDrawerOpened(drawerView: View) {

                //setNavigationData();
            }
            override fun onDrawerClosed(drawerView: View) {

            }
            override fun onDrawerStateChanged(newState: Int) {

            }
        })
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }
            override fun onDrawerOpened(drawerView: View) {

                //setNavigationData();
            }
            override fun onDrawerClosed(drawerView: View) {

            }
            override fun onDrawerStateChanged(newState: Int) {

            }
        })

        binding.drawerToolbar.ivWallet.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoaderWalletActivity::class.java).putExtra("flag1","1")
            startActivity(intent)

        })

        binding.drawerToolbar.ivNotifi.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)

        })

        complete_profilee = intent.getStringExtra("complete_profile").toString()

        if(complete_profilee.equals("0")){
            completeDialog()
        }
//        else if (complete_profilee.equals("1")){
//            completeDialog()
//        }

        replaceFragment(HomeFragment())
        setNavigationBar()
        prepareNavMenuList()
        navMenuClickListener()

        // binding..setText(userPref.getName())
       // binding.header.tvUserName.setText("Pramod Gupta")
        binding.header.tvUserName.text = userPref.user.name
        binding.header.tvEmail.text = userPref.user.email
      //  tvEmail.text = userPref.user.email

        Glide.with(this).load(userPref.user.profileImage)
            .apply(RequestOptions.placeholderOf(R.drawable.user_image_place_holder))
            .apply(RequestOptions.errorOf(R.drawable.user_image_place_holder))
            .into(binding.header.imgUser)





        binding.drawerToolbar.toolbarUserImage.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)


        })


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle = setupDrawerToggle();
        drawerToggle?.isDrawerIndicatorEnabled = true;
        drawerToggle?.syncState();
        drawerToggle?.let { binding.drawerLayout.addDrawerListener(it) }
        //   setupDrawerContent(binding.nvView)

        //   binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //  binding.bottomNavigationView.selectedItemId = R.id.navigation_home
        binding.drawerToolbar.opendrawer.setOnClickListener {
            // open drawer here
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }






    }

   // binding.header.tvUserName.text = userPref.user.name
   // binding.header.tvEmail.text = userPref.user.email
    @SuppressLint("SetTextI18n")
    private fun setNavigationData() {
        binding.header.tvUserName.text = userPref.getSubUserName()
        binding.header.tvEmail.text = userPref.getEmail()

        if (!userPref.getUserProfileImage().isNullOrBlank()) {
            Glide.with(this).load(Uri.parse(userPref.getUserProfileImage()))
                .apply(RequestOptions.placeholderOf(R.drawable.user_image_place_holder))
                .apply(RequestOptions.errorOf(R.drawable.user_image_place_holder))
                .into(binding.header.imgUser)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (exit) {
                super.onBackPressed()
                finish() // finish activity
            } else {
                utils.toaster("Press Back again to Exit.")
                exit = true
                Handler().postDelayed({ exit = false }, (3 * 1000).toLong())
            }
        }
    }
    private fun setNavigationBar() {
        binding.nvView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        prepareNavMenuList()
        navMenuClickListener()

        binding.drawerToolbar.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })
    }

    private fun prepareNavMenuList() {

        menuList!!.clear()
        menuList!!.add(DashboardMenuModel(this.getString(R.string.myprofile)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.favouritelocations)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.notifications)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.transactions)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.myoffers)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.settings)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.contactus)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.aboutus)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.cancellation)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.rateapplication)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.referandearn)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.term_and_condition)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.privacypolicy)))
        menuList!!.add(DashboardMenuModel(this.getString(R.string.logout)))

        menuListAdapter = MenuListAdapter(this, menuList!!)
        binding.listVideMenu.adapter = menuListAdapter
    }

    private fun navMenuClickListener() {

        binding.listVideMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                when (position) {
                0 -> {
                    val intent = Intent(this, MyProfileActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(this, AddFavouriteLocationsActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, NotificationActivity::class.java)
                    startActivity(intent)
                }
                3 -> {
                    val intent = Intent(this, TransactionHistoryActivity::class.java)
                    startActivity(intent)
                }
                4 -> {
                    val intent = Intent(this, MyOffersActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
                6 -> {
                    val intent = Intent(this, ContactUsActivity::class.java)
                    startActivity(intent)
                    }
                7 -> {
                val intent = Intent(this, AboutUsActivity::class.java)
                startActivity(intent)
                } 8 -> {
                val intent = Intent(this, CancellationPolicy::class.java)
                startActivity(intent)
                }
                    9 -> {
                    /*val intent = Intent(this, Rate::class.java)
                    startActivity(intent)*/
                } 10 -> {
                    val intent = Intent(this, ReferEarnActivity::class.java)
                    startActivity(intent)
                } 11 -> {
                    val intent = Intent(this, TermsConditionsActivity::class.java)
                    startActivity(intent)
                } 12  -> {
                    val intent = Intent(this, PrivacyPolicyActivity::class.java)
                    startActivity(intent)
                }
                    13 -> {
                        logout()
                    }
                }
                closeDrawer()
            }
    }
    private fun closeDrawer() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        return ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.drawer_open, R.string.drawer_close
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    private fun logout() {
        val cDialog = Dialog(this, R.style.Theme_Tasker_Dialog)
        val bindingDialog: DialogLogoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_logout,
            null,
            false
        )
        cDialog.setContentView(bindingDialog.root)
        cDialog.setCancelable(false)
        cDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        cDialog.show()
        bindingDialog.btnCancel.setOnClickListener {
            cDialog.dismiss()
        }
        bindingDialog.btnLogout.setOnClickListener {
//            userPref.clearPref()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finishAffinity()
//            cDialog.dismiss()
            userPref.isLogin=false
            userPref.clearPref()
            finishAffinity()
            startActivity(Intent(this, ChooseLanguageActivity::class.java))
            cDialog!!.dismiss()
        }
    }


    private fun completeDialog() {
        val cDialog = Dialog(this, R.style.Theme_Tasker_Dialog)
        val bindingDialog: DialogCompleteProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_complete_profile,
            null,
            false
        )
        cDialog.setContentView(bindingDialog.root)
        cDialog.setCancelable(false)
        cDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        cDialog.show()
        bindingDialog.btnOk.setOnClickListener {

            val intent = Intent(this, MyProfileActivity::class.java)
            //intent.putExtra("complete_profile", completeprofiletext)
            startActivity(intent)

            cDialog.dismiss()
        }
    }


    override fun onResume() {
        super.onResume()

        Glide.with(this).load(userPref.user.profileImage)
            .apply(RequestOptions.placeholderOf(R.drawable.user_image_place_holder))
            .apply(RequestOptions.errorOf(R.drawable.user_image_place_holder))
            .into(binding.drawerToolbar.toolbarUserImage)
    }

}