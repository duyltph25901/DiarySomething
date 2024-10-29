package com.example.baseprojectflamingo.ui.pin.se_qs

import android.animation.ObjectAnimator
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseActivity
import com.example.baseprojectflamingo.commons.base.ext.click
import com.example.baseprojectflamingo.databinding.ActivitySeQsBinding
import com.example.baseprojectflamingo.databinding.PopupMenuListQsBinding
import com.example.baseprojectflamingo.ui.pin.se_qs.QuestionUtil.getListQuestion
import com.example.baseprojectflamingo.ui.pin.se_qs.adapter.QuestionAdapter
import kotlin.math.roundToInt


class SecurityQsActivity : BaseActivity<ActivitySeQsBinding>(R.layout.activity_se_qs) {

    private var isRotated = false

    private lateinit var popupMenuBinding: PopupMenuListQsBinding
    private lateinit var popupWindow: PopupWindow
    private lateinit var listQsAdapter: QuestionAdapter

    override fun initVariables() {
        initPopMenuBinding()
        initTxtQs()
    }

    override fun initView() {
    }

    override fun observeData() {
    }

    override fun clickViews() {
        binding.cardQuestion.click {
            if (isRotated) popupWindow.dismiss()
            else showListQuestion(binding.cardQuestion)
            rotateIcon()
        }

        binding.icBack.click { finish() }

        binding.edtAns.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val isInputNull = p0?.isEmpty() ?: true
                binding.btnConfirm.apply {
                    setTextColor(
                        getColor(
                            if (isInputNull) R.color.txt_white
                            else R.color.txt_primary_1
                        )
                    )
                    isActivated = !isInputNull
                }
            }

            override fun afterTextChanged(p0: Editable?) = Unit
        })
    }

    private fun initPopMenuBinding() {
        val inflater = LayoutInflater.from(this)
        popupMenuBinding = PopupMenuListQsBinding.inflate(inflater)

        popupWindow = PopupWindow(
            popupMenuBinding.root,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        ).apply {
            isOutsideTouchable = false
            isFocusable = false
        }

        popupMenuBinding.rcvSeQs.apply {
            listQsAdapter = QuestionAdapter(this@SecurityQsActivity) { question, index ->
                binding.txtQs.text = getString(question.questionRes)
                if (isRotated) popupWindow.dismiss()
                else showListQuestion(binding.cardQuestion)
                rotateIcon()
            }.apply {
                submitData(getListQuestion())
            }

            adapter = listQsAdapter
        }
    }

    private fun initTxtQs() {
        binding.txtQs.text = getString(
            listQsAdapter.list[0].questionRes
        )
    }

    private fun rotateIcon() {
        val rotation = if (isRotated) 0f else 180f
        val animator = ObjectAnimator.ofFloat(binding.icDown, "rotation", rotation)
        animator.setDuration(400) // Thời gian xoay
        animator.start()
        isRotated = !isRotated // Đảo ngược trạng thái
    }

    private fun showListQuestion(view: View) {
        val location = IntArray(2)
        view.getLocationOnScreen(location)

        // Chuyển đổi 12dp thành pixel
        val offsetY = (4 * resources.displayMetrics.density).roundToInt()

        popupWindow.showAsDropDown(view, 0, offsetY)
    }
}