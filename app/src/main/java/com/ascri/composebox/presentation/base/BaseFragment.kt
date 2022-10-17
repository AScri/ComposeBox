package com.ascri.composebox.presentation.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ascri.composebox.R
import com.ascri.composebox.presentation.common.helpers.OnBackListener
import com.ascri.composebox.utils.extensions.addBackListener
import com.ascri.composebox.utils.extensions.isPermissionGranted
import com.ascri.composebox.utils.extensions.showToast
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment(), OnBackListener {
    private var permissionRationaleDialog: PermissionRationaleDialog? = null
    private var permissionResultAction: ((isAllowed: Boolean) -> Unit)? = null
    private var permissionsResultAction: ((Map<String, Boolean>) -> Unit)? = null
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            permissionResultAction?.invoke(it)
        }
    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissionsResultAction?.invoke(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBackListener(viewLifecycleOwner, this)
    }

    override fun onBack() {
        findNavController().navigateUp()
    }

    @Deprecated("Use registerForActivityResult functions")
    fun isPermissionsAllowed(
        permissions: Array<String>,
        shouldRequestIfNotAllowed: Boolean = false,
        requestCode: Int = -1
    ): Boolean {
        var isGranted = true

        for (permission in permissions) {
            isGranted = context.isPermissionGranted(permission)
            if (!isGranted) {
                break
            }
        }
        if (!isGranted && shouldRequestIfNotAllowed) {
            if (requestCode == -1) {
                throw RuntimeException("Send request code in third parameter")
            }
            requestRequiredPermissions(permissions, requestCode)
        }

        return isGranted
    }

    fun performPermissionsAction(
        permissions: Array<String>,
        shouldRequestIfNotAllowed: Boolean = false,
        deniedAction: (() -> Unit)? = {
            Snackbar.make(
                requireView(),
                R.string.permission_not_granted,
                Snackbar.LENGTH_SHORT
            ).show()
        },
        grantedAction: (() -> Unit)? = null
    ) {
        permissionsResultAction =
            { if (isAllPermissionsGranted(it.toMutableMap())) grantedAction?.invoke() else deniedAction?.invoke() }
        var isGranted = true

        for (permission in permissions) {
            isGranted = context.isPermissionGranted(permission)
            if (!isGranted) {
                break
            }
        }
        if (isGranted) grantedAction?.invoke()
        else if (shouldRequestIfNotAllowed) requestRequiredPermissions(permissions)
        else deniedAction?.invoke()
    }

    fun isAllPermissionsGranted(grantResults: IntArray): Boolean {
        var isGranted = true
        for (grantResult in grantResults) {
            isGranted = grantResult == PackageManager.PERMISSION_GRANTED
            if (!isGranted)
                break
        }
        return isGranted
    }

    fun isAllPermissionsGranted(grantResults: MutableMap<String, Boolean>): Boolean {
        var isGranted = true
        for (grantResult in grantResults) {
            isGranted = grantResult.value
            if (!isGranted)
                break
        }
        return isGranted
    }

    private fun requestRequiredPermissions(permissions: Array<String>, requestCode: Int) {
        val pendingPermissions: ArrayList<String> = ArrayList()
        permissions.forEachIndexed { _, permission ->
            if (!context.isPermissionGranted(permission)) {
                pendingPermissions.add(permission)
            }
        }
        val array = arrayOfNulls<String>(pendingPermissions.size)
        pendingPermissions.toArray(array)
        requestPermissions(array, requestCode)
    }

    private fun requestRequiredPermissions(permissions: Array<String>) {
        val pendingPermissions: ArrayList<String> = ArrayList()
        permissions.forEachIndexed { _, permission ->
            if (!context.isPermissionGranted(permission)) {
                pendingPermissions.add(permission)
            }
        }
        val array = arrayOfNulls<String>(pendingPermissions.size)

        requestPermissions.launch(pendingPermissions.toTypedArray())
    }

    protected fun performStorageAction(
        showPermissionRationale: Boolean = true,
        @StringRes error: Int = R.string.permission_not_granted,
        deniedAction: () -> Unit = { showToast(error) },
        grantedAction: () -> Unit
    ) = askPermission(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        showPermissionRationale,
        deniedAction,
        grantedAction
    )

    protected fun performCameraAction(
        showPermissionRationale: Boolean = true,
        @StringRes error: Int = R.string.permission_not_granted,
        deniedAction: () -> Unit = { showToast(error) },
        grantedAction: () -> Unit
    ) = askPermission(
        Manifest.permission.CAMERA,
        showPermissionRationale,
        deniedAction,
        grantedAction
    )

    protected fun performMicrophoneAction(
        showPermissionRationale: Boolean = true,
        @StringRes error: Int = R.string.permission_not_granted,
        deniedAction: () -> Unit = { showToast(error) },
        grantedAction: () -> Unit
    ) = askPermission(
        Manifest.permission.RECORD_AUDIO,
        showPermissionRationale,
        deniedAction,
        grantedAction
    )

    private fun askPermission(
        permission: String,
        showPermissionRationale: Boolean,
        deniedAction: () -> Unit,
        grantedAction: () -> Unit
    ) = if (context.isPermissionGranted(permission)) {
        grantedAction.invoke()
    } else {
        permissionResultAction = { if (it) grantedAction.invoke() else deniedAction.invoke() }
        if (shouldShowRequestPermissionRationale(permission) && showPermissionRationale) {
            showPermissionRationaleDialog(permission)
        } else {
            requestPermission.launch(permission) //showPermissionSystemDialog
        }
    }

    private fun showPermissionRationaleDialog(permission: String) {
        permissionRationaleDialog?.dismiss()
        permissionRationaleDialog = PermissionRationaleDialog().apply {
            onRetry = { requestPermission.launch(permission) } //showPermissionSystemDialog
        }
        permissionRationaleDialog?.show(childFragmentManager)
    }

}
