package com.example.baseprojectflamingo.ui.pin.se_qs.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseRecyclerView
import com.example.baseprojectflamingo.commons.base.ext.click
import com.example.baseprojectflamingo.databinding.ItemQuestionBinding
import com.example.baseprojectflamingo.ui.pin.se_qs.model.Question

class QuestionAdapter(
    private val contextParams: Context,
    private val onChooseQuestion: (question: Question, index: Int) -> Unit
) : BaseRecyclerView<Question>() {
    override fun getItemLayout(): Int = R.layout.item_question

    override fun setData(binding: ViewDataBinding, item: Question, layoutPosition: Int) {
        if (binding is ItemQuestionBinding) {
            binding.itemSeQs.text = contextParams.getString(item.questionRes)
        }
    }

    override fun onClickViews(binding: ViewDataBinding, obj: Question, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)
        if (binding is ItemQuestionBinding) {
            binding.root.click { onChooseQuestion.invoke(obj, layoutPosition) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<Question>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
}