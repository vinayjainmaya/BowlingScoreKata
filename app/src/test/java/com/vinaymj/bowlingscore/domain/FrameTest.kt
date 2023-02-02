package com.vinaymj.bowlingscore.domain

import org.junit.Assert
import org.junit.Test


class FrameTest {

    private val frame = Frame()

    @Test
    fun `initial score`() {
        Assert.assertEquals(0, frame.score(0,0))
    }

    @Test
    fun `normal frame score`() {
        Assert.assertEquals(8, frame.score(5,3))
    }

    @Test
    fun `spare frame score`() {
        Assert.assertEquals(10, frame.score(5,5))
    }
}