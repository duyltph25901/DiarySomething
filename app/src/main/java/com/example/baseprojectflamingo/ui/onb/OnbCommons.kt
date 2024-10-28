package com.example.baseprojectflamingo.ui.onb

import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.ui.onb.model.OnbModel

object OnbCommons {

    private fun getListAnswerOnbPage1() = mutableListOf(
        OnbModel.AnswerOnb(0, R.string.ans_onb_1_1),
        OnbModel.AnswerOnb(1, R.string.ans_onb_1_2),
        OnbModel.AnswerOnb(2, R.string.ans_onb_1_3),
        OnbModel.AnswerOnb(3, R.string.ans_onb_1_4)
    )

    fun getListAnswerOnbPage2() = mutableListOf(
        OnbModel.AnswerOnb(4, R.string.ans_ong_2_1),
        OnbModel.AnswerOnb(5, R.string.ans_ong_2_2),
        OnbModel.AnswerOnb(6, R.string.ans_ong_2_3),
        OnbModel.AnswerOnb(7, R.string.ans_ong_2_4),
        OnbModel.AnswerOnb(8, R.string.ans_ong_2_5),
    )

    private fun getListAnswerOnbPage3() = mutableListOf(
        OnbModel.AnswerOnb(9, R.string.ans_ong_3_1),
        OnbModel.AnswerOnb(10, R.string.ans_ong_3_2),
        OnbModel.AnswerOnb(11, R.string.ans_ong_3_3),
        OnbModel.AnswerOnb(12, R.string.ans_ong_3_4),
        OnbModel.AnswerOnb(13, R.string.ans_ong_3_5),
    )

    fun getListOnb() = mutableListOf(
        OnbModel(0L, R.string.onb_title_1, R.string.please_select_one_option, getListAnswerOnbPage1()),
        OnbModel(1L, R.string.onb_title_2, R.string.please_select_one_option, getListAnswerOnbPage2()),
        OnbModel(2L, R.string.onb_title_3, R.string.you_can_select_more_than_one_option, getListAnswerOnbPage3()),
    )
}