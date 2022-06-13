package com.pratical.utility

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD

object Globals {
    @SuppressLint("StaticFieldLeak")
    private var progressFlower: KProgressHUD? = null

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    //show progress dialog
    fun showProgress(context: Context) {
        progressFlower = ProgressUtil.instance!!.initProgressBar(context)
        if (!progressFlower!!.isShowing) progressFlower!!.show()
    }

    //hide progress dialog
    fun hideProgress() {
        if (progressFlower != null) {
            if (progressFlower!!.isShowing) progressFlower!!.dismiss()
        }
    }

    // hide keyboard
    fun hideKeyboard(activity: Activity) {
        try {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            var focusedView = activity.currentFocus
            if (focusedView == null) {
                focusedView = View(activity)
            }
            inputManager.hideSoftInputFromWindow(
                focusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
