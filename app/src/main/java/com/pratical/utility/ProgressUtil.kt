package com.pratical.utility

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

class ProgressUtil {

    fun initProgressBar(context: Context?): KProgressHUD {
        return KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(1)
            .setDimAmount(0.5f)
            .show()
    }

    companion object {
        private var mInstance: ProgressUtil? = null
        val instance: ProgressUtil?
            get() {
                if (mInstance == null) {
                    mInstance = ProgressUtil()
                }
                return mInstance
            }
    }
}