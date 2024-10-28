package com.example.baseprojectflamingo.ui.onb.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseRecyclerView
import com.example.baseprojectflamingo.databinding.ItemOnboardingBinding
import com.example.baseprojectflamingo.ui.onb.model.OnbModel

class OnbAdapter(
    private val contextParams: Context,
    private val answerAdapter: AnswerAdapter
) : BaseRecyclerView<OnbModel>() {
    override fun getItemLayout(): Int = R.layout.item_onboarding

    override fun setData(binding: ViewDataBinding, item: OnbModel, layoutPosition: Int) {
        if (binding is ItemOnboardingBinding) {
            binding.txtTitleOnb.text = contextParams.getString(item.titleRes)
            binding.txtDesOnb.text = contextParams.getString(item.descriptionRes)
            binding.rcvAnswer.apply {
                adapter = answerAdapter.apply {
                    submitData(item.listAnswer)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<OnbModel>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
}