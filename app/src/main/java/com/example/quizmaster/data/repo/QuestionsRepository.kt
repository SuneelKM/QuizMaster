package com.example.quizmaster.data.repo

import com.example.quizmaster.data.api.RetroApiInterface
import javax.inject.Inject


class QuestionsRepository @Inject constructor (val inter: RetroApiInterface) {

    fun getQuestions(
        amount: String,
        category: String,
        difficulty: String,
        type: String
    ) = inter.getQuestions(amount, category, difficulty, type)
}




