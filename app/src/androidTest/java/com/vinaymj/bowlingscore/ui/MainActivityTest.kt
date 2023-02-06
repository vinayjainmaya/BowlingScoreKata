package com.vinaymj.bowlingscore.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vinaymj.bowlingscore.ui.theme.BowlingScoreKataTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            BowlingScoreKataTheme {
                MainScreen()
            }
        }
    }

    @Test
    fun shouldDisplayInitialScreen() {
        composeTestRule.onNodeWithTag("matchResult").assertIsDisplayed()
        composeTestRule.onNodeWithTag("frame1").assertIsDisplayed()

        composeTestRule.onNodeWithTag("First Roll").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Second Roll").assertIsDisplayed()

        composeTestRule.onNodeWithTag("submitButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("resetButton").assertIsDisplayed()
    }
}