package com.vinaymj.bowlingscore.domain

data class FrameScores(
    val first: Int,
    val second: Int,
    val third: Int = 0, // 10th frame extra roll
    var gameTotal: Int = 0
) {
    val spare = ((first != 10 && second != 10) && (first + second) == 10)
    val strike = (first == 10 || second == 10)
    val frameTotal = first + second
}
