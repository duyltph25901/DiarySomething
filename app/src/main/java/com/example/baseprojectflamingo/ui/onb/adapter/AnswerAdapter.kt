package com.example.baseprojectflamingo.ui.onb.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseRecyclerView
import com.example.baseprojectflamingo.commons.base.ext.click
import com.example.baseprojectflamingo.databinding.ItemAnswerBinding
import com.example.baseprojectflamingo.ui.onb.model.OnbModel

class AnswerAdapter(
    private val contextParams: Context,
    private val onChooseAnswer: (ans: OnbModel.AnswerOnb, index: Int) -> Unit
) : BaseRecyclerView<OnbModel.AnswerOnb>() {
    override fun getItemLayout(): Int = R.layout.item_answer

    override fun setData(binding: ViewDataBinding, item: OnbModel.AnswerOnb, layoutPosition: Int) {
        if (binding is ItemAnswerBinding) {
            binding.txtAnswers.text = contextParams.getString(item.answerRes)
            val colorBgAns =
                if (item.isSelected) R.color.bg_ans_selected else R.color.bg_ans_un_selected
            val colorTxtAns =
                if (item.isSelected) R.color.txt_ans_selected else R.color.txt_ans_un_selected

            binding.txtAnswers.apply {
                setTextColor(contextParams.getColor(colorTxtAns))
                setBackgroundColor(ContextCompat.getColor(contextParams, colorBgAns))
            }
        }
    }

    override fun onClickViews(
        binding: ViewDataBinding,
        obj: OnbModel.AnswerOnb,
        layoutPosition: Int
    ) {
        super.onClickViews(binding, obj, layoutPosition)
        if (binding is ItemAnswerBinding) {
            binding.root.click {
                onChooseAnswer.invoke(obj, layoutPosition)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<OnbModel.AnswerOnb>) {
        list.apply {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }
}