package com.vinaymj.bowlingscore.domain


class Frame {

    fun score(firstPoint: Int, secondPoint: Int): Int {
        return firstPoint + secondPoint
    }

    fun score(firstPoint: Int, secondPoint: Int, currentScore: Int): Int {
        return currentScore + score(firstPoint, secondPoint)
    }

}