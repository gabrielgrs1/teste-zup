package br.com.gabrielgrs.zuptest.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import br.com.gabrielgrs.zuptest.R
import java.util.*

/**
 * Created by gabrielgrs
 * Date: 2019-05-01
 * Time: 13:33
 * Project: ZUPTest
 */
class GenericProgressDialog : androidx.fragment.app.DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return AlertDialog.Builder(Objects.requireNonNull<androidx.fragment.app.FragmentActivity>(activity))
            .setView(
                activity!!
                    .layoutInflater
                    .inflate(R.layout.dialog_generic_progress, null)
            )
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Objects.requireNonNull<Window>(dialog?.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun show(manager: androidx.fragment.app.FragmentManager) {
        if (show || dialog != null && dialog!!.isShowing) return

        try {
            show = true
            super.show(manager, TAG)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun dismiss() {
        try {
            show = false
            super.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {

        private const val TAG = "generic_loading"
        private var genericProgressDialog: GenericProgressDialog? = null
        private var show = false

        val instance: GenericProgressDialog
            @Synchronized get() {
                if (genericProgressDialog == null) {
                    genericProgressDialog = GenericProgressDialog()
                }
                return genericProgressDialog!!
            }
    }
}