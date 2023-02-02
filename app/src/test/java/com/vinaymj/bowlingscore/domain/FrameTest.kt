package com.vinaymj.bowlingscore.domain

import org.junit.Assert
import org.junit.Test


class FrameTest {

    private val frame = Frame()

    @Test
    fun `initial score`() {
        Assert.assertEquals(0, frame.score(0,0))
    }

}