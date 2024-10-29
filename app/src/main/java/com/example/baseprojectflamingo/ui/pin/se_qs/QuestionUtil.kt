package com.example.baseprojectflamingo.ui.pin.se_qs

import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.ui.pin.se_qs.model.Question

object QuestionUtil {

    fun getListQuestion() = mutableListOf(
        Question(0, R.string.ans_onb_1_1),
        Question(1, R.string.ans_onb_1_2),
        Question(2, R.string.ans_onb_1_3),
        Question(3, R.string.ans_onb_1_4),
    )

}