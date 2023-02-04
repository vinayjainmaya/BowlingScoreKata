package com.vinaymj.bowlingscore.domain

import javax.inject.Inject


class Frame @Inject constructor() {

    fun score(firstPoint: Int, secondPoint: Int, currentScore: Int = 0): Int {
        return currentScore + firstPoint + secondPoint
    }

    fun scoreWithBonus(currentScore: Int, bonus: Int): Int {
        return currentScore + bonus
    }

}