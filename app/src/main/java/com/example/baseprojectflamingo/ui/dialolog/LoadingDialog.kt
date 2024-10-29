package com.example.baseprojectflamingo.ui.dialolog

import android.content.Context
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseDialog
import com.example.baseprojectflamingo.databinding.DialogLoadingBinding

class LoadingDialog(
    private val context: Context
) : BaseDialog<DialogLoadingBinding>(context) {

    override fun getLayoutDialog(): Int = R.layout.dialog_loading

    override fun initViews() {
        super.initViews()
        setCancelable(false)
        setAutoScrollHorizontalTxtDes()
    }

    private fun setAutoScrollHorizontalTxtDes() {
        binding.txtDes.isSelected = true
    }

}