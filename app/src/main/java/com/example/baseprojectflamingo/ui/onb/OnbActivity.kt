package com.example.baseprojectflamingo.ui.onb

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseActivity
import com.example.baseprojectflamingo.commons.base.ext.click
import com.example.baseprojectflamingo.commons.base.ext.hide
import com.example.baseprojectflamingo.commons.base.ext.show
import com.example.baseprojectflamingo.databinding.ActivityOnbBinding
import com.example.baseprojectflamingo.ui.onb.OnbCommons.getListOnb
import com.example.baseprojectflamingo.ui.onb.adapter.AnswerAdapter
import com.example.baseprojectflamingo.ui.onb.adapter.OnbAdapter
import com.example.baseprojectflamingo.ui.pin.set.SetPinActivity

@SuppressLint("NotifyDataSetChanged")
class OnbActivity : BaseActivity<ActivityOnbBinding>(R.layout.activity_onb) {

    private val answerAdapter: AnswerAdapter by lazy {
        AnswerAdapter(this) { ans, index ->
            val isPage1 = ans.id in 0..3
            val isPage2 = ans.id in 4..8
            val isPage3 = ans.id in 9..13

            // choose one option
            if (isPage1 || isPage2) {
                val indexSelectedBefore =
                    this@OnbActivity.answerAdapter.list.indexOfFirst { it.isSelected }
                val isNotSelectedBefore = indexSelectedBefore == -1
                if (isNotSelectedBefore) {
                    this@OnbActivity.answerAdapter.apply {
                        list[index].isSelected = true
                        notifyItemChanged(index)
                    }
                } else {
                    this@OnbActivity.answerAdapter.apply {
                        list.apply {
                            get(index).isSelected = true
                            get(indexSelectedBefore).isSelected = false
                        }
                        notifyItemChanged(index)
                        notifyItemChanged(indexSelectedBefore)
                    }
                }

                val isUnSelectedAllList = this@OnbActivity.answerAdapter.list.none { it.isSelected }
                binding.btnContinue.isActivated = !isUnSelectedAllList
            }

            // choose multiple options
            if (isPage3) {
                this@OnbActivity.answerAdapter.apply {
                    list[index].isSelected = !ans.isSelected
                    notifyItemChanged(index)
                }

                val isNoneSelectedInList =
                    this@OnbActivity.answerAdapter.list.none { it.isSelected }
                binding.btnContinue.isActivated = !isNoneSelectedInList
            }
        }
    }
    private val onbAdapter: OnbAdapter by lazy {
        OnbAdapter(this, answerAdapter).apply {
            submitData(getListOnb())
        }
    }
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private var indexIndicator = 0

    override fun initVariables() = Unit

    override fun initView() {
        initRcvOnb()
        initIndicatorDefault()
    }

    override fun observeData() = Unit

    override fun clickViews() {
        binding.btnContinue.click {
            onContinue()
        }

        binding.txtSkip.click {
            goToSetPinScreen()
        }
    }

    private fun changeUiIndicator(index: Int) {
        when (index) {
            0 -> {
                binding.indicator1.setImageResource(R.drawable.ic_ob_selected)
                binding.indicator2.setImageResource(R.drawable.ic_ob_un_selected)
                binding.indicator3.setImageResource(R.drawable.ic_ob_un_selected)
            }

            1 -> {
                binding.indicator1.setImageResource(R.drawable.ic_ob_un_selected)
                binding.indicator2.setImageResource(R.drawable.ic_ob_selected)
                binding.indicator3.setImageResource(R.drawable.ic_ob_un_selected)
            }

            else -> {
                binding.indicator1.setImageResource(R.drawable.ic_ob_un_selected)
                binding.indicator2.setImageResource(R.drawable.ic_ob_un_selected)
                binding.indicator3.setImageResource(R.drawable.ic_ob_selected)
            }
        }
    }

    private fun initRcvOnb() = binding.rcvOnb.apply {
        adapter = onbAdapter
        layoutManager = linearLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                val indexLastOnb = 2
                if (lastVisibleItemPosition == indexLastOnb) binding.txtSkip.hide()
                else binding.txtSkip.show()
            }
        })
    }

    private fun initIndicatorDefault() = changeUiIndicator(0)

    private fun onContinue() {
        val canContinue = binding.btnContinue.isActivated
        if (canContinue) {
            val lastIndexOnb = 2
            val isLastItemOnb = indexIndicator >= lastIndexOnb
            if (isLastItemOnb) goToSetPinScreen()
            else {
                indexIndicator++
                binding.rcvOnb.scrollToPosition(indexIndicator)
                changeUiIndicator(indexIndicator)
                disableBtnContinue()
            }

        } else Log.e("duylt", "onContinue failure")
    }

    private fun disableBtnContinue() {
        binding.btnContinue.isActivated = false
    }

    private fun goToSetPinScreen() {
        startActivity(Intent(this, SetPinActivity::class.java))
        finish()
    }
}