package com.vinaymj.bowlingscore.domain

data class FrameScores(
    val first: Int,
    val second: Int,
    var total: Int = 0
) {
    val spare = (first + second) == 10
}
