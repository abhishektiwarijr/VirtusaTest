package com.jr.virtusatest.presentation.dog_list

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jr.virtusatest.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DogListScreenTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
    }

    @Test
    fun check_DogListScreen_exists() {
        composeTestRule.activity.setContent {
            DogListScreen(
                navController = rememberNavController(),
                viewModel = composeTestRule.activity.viewModels<DogListViewModel>().value
            )
        }
        composeTestRule.onNodeWithTag(TEST_TAG_DOG_LIST_SCREEN).assertExists()
    }
}