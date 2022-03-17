package com.solutionsmax.gurukoolmax_v3.core.ui.base

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.navigation.MainNavigation
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {
    val mainNavController: NavController? by lazy {
        val mainActivity = activity
        if (mainActivity != null && mainActivity is MainNavigation) {
            mainActivity.mainNavController
        } else {
            null
        }
    }

    var sToken: String = ""
    var sBaseURL: String = ""
    var iGroupID: Int = -1
    var iBranchID: Int = -1
    var allPermissionGranted: Boolean = false
    val currentNavController: NavController by lazy { findNavController() }

    private var alertDialog: AlertDialog? = null

    fun showAlertDialog(
        title: String,
        message: CharSequence,
        onClick: (DialogInterface, Int) -> Unit
    ) {
        if (alertDialog != null) {
            alertDialog?.apply {
                setTitle(title)
                setMessage(message)
                if (!isShowing) {
                    show()
                }
            }
        } else {
            alertDialog = AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.okay), onClick)
                .show()
        }
    }

    fun showError(
        title: String,
        message: String,
        onClick: (DialogInterface, Int) -> Unit = { _, _ -> }
    ) {
        showAlertDialog(
            title = title,
            message = message,
            onClick = onClick
        )
    }

    fun checkPermission(): Boolean {
        Dexter.withContext(context).withPermissions(
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                p0?.let {
                    allPermissionGranted = it.areAllPermissionsGranted()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }

        })
            .withErrorListener { Toast.makeText(context, it.name, Toast.LENGTH_LONG).show() }
            .check()
        return allPermissionGranted
    }
}