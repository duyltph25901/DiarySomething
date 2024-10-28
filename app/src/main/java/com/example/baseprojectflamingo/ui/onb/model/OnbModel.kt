package com.example.baseprojectflamingo.ui.onb.model

data class OnbModel(
    var id: Long = 0L,
    var titleRes: Int = 0,
    var descriptionRes: Int = 0,
    var listAnswer: MutableList<AnswerOnb> = mutableListOf()
) {

    data class AnswerOnb(
        var id: Long = 0L,
        var answerRes: Int = 0,
        var isSelected: Boolean = false
    )

}