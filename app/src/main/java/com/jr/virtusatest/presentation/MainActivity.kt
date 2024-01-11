package com.jr.virtusatest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jr.virtusatest.common.Constants
import com.jr.virtusatest.presentation.dog_details.DogDetailsScreen
import com.jr.virtusatest.presentation.dog_details.DogDetailsViewModel
import com.jr.virtusatest.presentation.dog_list.DogListScreen
import com.jr.virtusatest.presentation.dog_list.DogListViewModel
import com.jr.virtusatest.presentation.ui.theme.VirtusaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            VirtusaTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DogListScreen.route
                    ) {
                        val dogListViewModel: DogListViewModel by viewModels<DogListViewModel>()
                        composable(
                            route = Screen.DogListScreen.route
                        ) {
                            DogListScreen(
                                navController = navController,
                                viewModel = dogListViewModel
                            )
                        }
                        val dogDetailsViewModel: DogDetailsViewModel by viewModels<DogDetailsViewModel>()
                        composable(
                            route = Screen.DogDetailScreen.route + "/{${Constants.PARAM_DOG_BREED_NAME}}" + "/{${Constants.PARAM_DOG_FULL_NAME}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_DOG_BREED_NAME) { type = NavType.StringType },
                                navArgument(Constants.PARAM_DOG_FULL_NAME) { type = NavType.StringType },
                            )
                        ) { navBackStackEntry ->
                            val dogBreedName = navBackStackEntry.arguments?.getString(Constants.PARAM_DOG_BREED_NAME)
                            val dogFullName = navBackStackEntry.arguments?.getString(Constants.PARAM_DOG_FULL_NAME)
                            DogDetailsScreen(
                                dogBreedName = dogBreedName,
                                dogFullName = dogFullName,
                                viewModel = dogDetailsViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}