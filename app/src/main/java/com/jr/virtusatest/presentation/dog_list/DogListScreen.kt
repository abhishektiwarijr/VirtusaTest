package com.jr.virtusatest.presentation.dog_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.jr.virtusatest.domain.model.DogName
import com.jr.virtusatest.presentation.Screen

const val TEST_TAG_DOG_LIST_SCREEN = "dog_list_screen"

@Composable
fun DogListScreen(
    navController: NavController,
    viewModel: DogListViewModel
) {
    val state = viewModel.dogListState.collectAsStateWithLifecycle().value
    Box(modifier = Modifier
        .fillMaxSize()
        .testTag(TEST_TAG_DOG_LIST_SCREEN)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.dogBreeds) { index, dogName ->
                DogListItem(
                    dogItemIndex = "${index + 1}",
                    dogName = dogName,
                    onItemClick = {
                        navController.navigate(
                            Screen.DogDetailScreen.route + "/${dogName.dogBreedName}" + "/${dogName.dogFullName}"
                        )
                    }
                )
            }
        }
        if (state.error.isNullOrBlank().not()) {
            Text(
                text = state.error.toString(),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun DogListItem(
    dogItemIndex: String,
    dogName: DogName,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(dogName.dogFullName) }
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dogItemIndex,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = dogName.dogFullName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}