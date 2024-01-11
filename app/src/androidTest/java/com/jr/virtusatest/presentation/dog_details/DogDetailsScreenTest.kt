package com.jr.virtusatest.presentation.dog_details

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
class DogDetailsScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
    }

    @Test
    fun check_DogDetailsScreen_exists() {
        composeTestRule.activity.setContent {
            DogDetailsScreen(
                dogBreedName = "affenpinscher",
                dogFullName = "Affenpinscher",
                viewModel = composeTestRule.activity.viewModels<DogDetailsViewModel>().value
            )
        }
        composeTestRule.onNodeWithTag(TEST_TAG_DOG_DETAILS_SCREEN).assertExists()
    }
}