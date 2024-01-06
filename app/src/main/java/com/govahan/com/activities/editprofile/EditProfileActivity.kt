package com.govahan.com.activities.editprofile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityEditProfileBinding
import com.govahan.com.util.CommonUtils
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EditProfileActivity : BaseActivity() {
    private lateinit var binding : ActivityEditProfileBinding
    private var userimage : MultipartBody.Part? = null
    private val pickImageCamera = 1
    private val pickImageGallery = 2
    private lateinit var currentPhotoPath: String
    private var mPhotoFile: File? = null
    private var photoURICamera: Uri?=null
    private val viewModel : EditProfileViewModel by viewModels()
    private lateinit var pickSingleMediaLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

        if (userPref.getUserProfileImage().equals("")){

        }else{
            Glide.with(this).load(userPref.getUserProfileImage())
                .apply(RequestOptions.placeholderOf(R.drawable.user_image_place_holder))
                .apply(RequestOptions.errorOf(R.drawable.user_image_place_holder))
                .into(binding.ivPict)
          }
        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.ivCam.setOnClickListener {
            selectImage()
        }

        binding.etName.setText(userPref.user.name)
        binding.edtPhonenumber.setText(userPref.user.mobileNumber)
        binding.etEmail.setText(userPref.user.email)

        pickSingleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                Log.d("TAG", "@@onActivityResult:.....")
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(this, "Failed picking media.", Toast.LENGTH_SHORT).show()
                } else {
                    val uri = it.data?.data
                    Glide.with(this).load(uri).placeholder(R.drawable.profile).into(binding.ivPict).toString()
                    val path = getPathFromURI(uri)

                    if (path != null) {
                        mPhotoFile = File(path)
                        Glide.with(this).load(mPhotoFile).into(binding.ivPict)
                        //   images.add(imageFile!!.absolutePath.toString())
                    }

                    val requestFile: RequestBody =
                        mPhotoFile!!.asRequestBody("image/*".toMediaTypeOrNull())

                    userimage = MultipartBody.Part.createFormData(
                        "image",
                        mPhotoFile!!.name,
                        requestFile
                    )
                }
            }
        if (userPref.user.address != null) {
            binding.etAddress.setText(userPref.user.address)
        }

        binding.btnUpdate.setOnClickListener {
            if (userimage == null){
                userimage = MultipartBody.Part.createFormData("profile_image", "")
            }
            if (validate()){
                viewModel.updateUserProfile("Bearer " + userPref.user.apiToken ,
                    binding.etName.text.trim().toString(),
                    binding.etEmail.text.trim().toString(),
                    userPref.user.mobileNumber!!,
                    binding.etAddress.text.trim().toString(),
                    userPref.user.deviceToken!!,
                    userPref.user.deviceType!!,
                    userPref.user.deviceId!!,
                    userimage!!)
            }
        }

        viewModel.progressBarStatus.observe(this, androidx.lifecycle.Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })

        viewModel.updateUserProfileResponse.observe(this, androidx.lifecycle.Observer {
            if (it.status == 1) {
                userPref.isLogin = true
                userPref.user = it.loginResponseData!!
                userPref.setToken(it.loginResponseData!!.apiToken)
                userPref.setSubUserName(it.loginResponseData!!.name)
                userPref.setEmail(it.loginResponseData!!.email)
                userPref.setMobile(it.loginResponseData!!.mobileNumber)
                userPref.setAddress(it.loginResponseData!!.address)
                userPref.setSubUserId(it.loginResponseData!!.id.toString())
                if (it.loginResponseData!!.profileImage != null) {
                    userPref.setProfileImage(it.loginResponseData!!.profileImage)
                    Log.e("@@image", userPref.getUserProfileImage().toString())
                }
                toast("Profile Updated Successfully...")
                finish()
            } else {
                hideProgressDialog()
                toast(it.message!!)
            }
        })
    }
    private fun getPathFromURI(contentUri: Uri?): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? =
            this.contentResolver.query(contentUri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageCamera) {
            binding.ivPic.setImageURI(photoURICamera)
            val file = File(currentPhotoPath)

            val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
            userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)

        } else if (requestCode == pickImageGallery && data != null) {
            val selectedImage = data.data
            try {
               binding.ivPic.setImageURI(selectedImage)
                val file = File(getPath(selectedImage!!))
                binding.ivPic.setImageURI(selectedImage)
                val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
                userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageCamera) {
            binding.ivPict.setImageURI(photoURICamera)
            val file = File(currentPhotoPath)
            val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)

            userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)
           // Toast.makeText(this, ":cam:  "+userimage, Toast.LENGTH_SHORT).show()
            System.out.println("::11111cam3  "+userimage)

        } else if (requestCode == pickImageGallery && data != null) {
            val selectedImage = data.data
            try {
                binding.ivPict.setImageURI(selectedImage)
                val file = File(getPath(selectedImage!!))
                val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
                userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)
              //  Toast.makeText(this, ":file:  "+userimage, Toast.LENGTH_SHORT).show()
                System.out.println("::11111cam4  "+userimage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageCamera) {
            binding.ivPict.setImageURI(photoURICamera)
            val file = File(currentPhotoPath)
            val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)

            userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)
        } else if (requestCode == pickImageGallery && data != null) {
            val selectedImage = data.data
            try {
                binding.ivPict.setImageURI(selectedImage)
                val file = File(getPath(selectedImage!!))
                val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
                userimage = MultipartBody.Part.createFormData("profile_image", file.name, requestFile)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun selectImage() {
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(this)
        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottom_drawer, null)

        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(true)

        val CameraButton = view.findViewById<TextView>(R.id.camera_open)
        CameraButton.setOnClickListener {
//            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

//            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
//                try {
//                    imageFile = createImageFile()!!
//                } catch (ex: IOException) {
//                }
//                if (imageFile != null) {

//                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
//                        intent.resolveActivity(packageManager)?.also {
//                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
//                        }
//                    }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                captureImage()

                // Setup max pick medias

            } else {
                captureImage()
//                captureImage()
//                val values = ContentValues()
//                values.put(MediaStore.Images.Media.TITLE, "New Picture")
//                values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
//                photoURICamera =
//                    this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURICamera)
//                startActivityForResult(takePictureIntent, pickImageCamera)

            }




            dialog.dismiss()
        }
//            }
//        }

        val GalleryButton = view.findViewById<TextView>(R.id.gallery_open)
        GalleryButton.setOnClickListener {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Setup pick 1 image/video
                pickSingleMediaLauncher.launch(Intent(MediaStore.ACTION_PICK_IMAGES))

                // Setup max pick medias

            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, pickImageGallery)

            }




//            startActivityForResult(intent, GALLERY)
            dialog.dismiss()
        }
        val cancel = view.findViewById<TextView>(R.id.cancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }

    private fun captureImage() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                // Create the File where the photo should go
                try {
                    mPhotoFile = createImageFile()
                    // Continue only if the File was successfully created
                    if (mPhotoFile != null) {
                        photoURICamera = FileProvider.getUriForFile(
                            this,
                            "com.govahanpartner.com.fileprovider",
                            mPhotoFile!!
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURICamera)

                        startActivityForResult(takePictureIntent, pickImageCamera)
                    }
                } catch (ex: Exception) {
                    // Error occurred while creating the File
//                    displayMessage(baseContext, ex.message.toString())
                }

            } else {
//                displayMessage(baseContext, "Null")
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->

            takePictureIntent.resolveActivity(this.packageManager)?.also {

                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri =
                        FileProvider.getUriForFile(
                            this,
                            "com.govahan.com.myUniquefileprovider",
                            it
                        )
                    mPhotoFile = photoFile
                    photoURICamera = photoURI
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, pickImageCamera)
                }

            }
        }

    }

    private fun getPath(uri: Uri): String {
        val data = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(this, uri, data, null, null, null)
        val cursor = loader.loadInBackground()
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        Log.d("image path", cursor.getString(column_index))
        return cursor.getString(column_index)
    }

    private fun requestStoragePermission(isCamera: Boolean) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
//                    if (report.areAllPermissionsGranted()) {
                        if (isCamera) {
                            openCamera()
                        } else {
                            openGallery()
                        }
//                    }
                    // check for permanent denial of any permission
//                    if (report.isAnyPermissionPermanentlyDenied) {
//                        // show alert dialog navigating to Settings
//                        showSettingsDialog()
//                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?, token: PermissionToken?) {
                    token!!.continuePermissionRequest()
                }
            })
            .withErrorListener {
            }
            .onSameThread()
            .check()
    }

    private fun openGallery() {
        val pickPhoto =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, pickImageGallery)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton(
            "GOTO SETTINGS"
        ) { dialog: DialogInterface, which: Int ->
            openSettings()
            dialog.cancel()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog: DialogInterface, which: Int -> dialog.cancel() }
        builder.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", "com.govahan.com", null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }


    fun validate() : Boolean {

        if (binding.etName.text.trim().isEmpty()) {
            binding.etName.error = "Please enter your name"
            binding.etName.requestFocus()
            return false
        } else if (!CommonUtils.isValidMail(binding.etEmail.text.toString().trim())) {
            binding.etEmail.error = "Please enter valid Email"
            binding.etEmail.requestFocus()
            return false
        }
        else if (binding.etEmail.text.trim().isEmpty()) {
            binding.etEmail.error = "Please enter valid Email"
            binding.etEmail.requestFocus()
            return false
        }

        else if (binding.etAddress.text.trim().isEmpty()) {
            binding.etAddress.error = "Please enter your address"
            binding.etAddress.requestFocus()
            return false
        }

        return true
    }

}