package com.vinaymj.bowlingscore.domain

data class FrameScores(
    val first: Int,
    val second: Int,
    var total: Int = 0
) {
    val spare = ((first != 10 && second != 10) && (first + second) == 10)
    val strike = (first == 10 || second == 10)
}
