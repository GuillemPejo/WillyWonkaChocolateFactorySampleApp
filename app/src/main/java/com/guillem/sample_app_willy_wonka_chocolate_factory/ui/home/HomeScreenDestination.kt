package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.home

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components.ProfessionChip
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.WillyWonkaChocolateFactoryTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.providers.AppTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.tooling.CombinedPreviews
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.Destination
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.DestinationDeclaration
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.ApiErrorHandling
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.toUi
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.utils.toGenderName
import org.koin.core.scope.Scope

class HomeScreenDestination : Destination<Unit>() {
    @Composable
    override fun Content(navEntry: NavBackStackEntry, diScope: Scope, outerArgs: Unit) {
        HomeScreen(diScope.get())
    }

    companion object : DestinationDeclaration<HomeScreenDestination>(
        HomeScreenDestination::class
    )
}

@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel) {
    val lazyWorkers = homeViewModel.workers.collectAsLazyPagingItems()

    HomeScreenContent(
        workersData = lazyWorkers,
        onItemClick = { workerId -> homeViewModel.onItemClick(workerId) },
    )
}

@Composable
private fun HomeScreenContent(
    workersData: LazyPagingItems<OompaLoompaUI>,
    onItemClick: (Int) -> Unit,
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.appPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                items(workersData) { worker ->
                    worker?.let {
                        WorkerItem(item = it, onItemClick = onItemClick)
                    }
                }

                when (workersData.loadState.append) {
                    is LoadState.Error -> {
                        item { ShowErrorItem(workersData.loadState.append as LoadState.Error) }
                    }
                    LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(42.dp)
                                    .padding(16.dp), strokeWidth = 5.dp
                            )
                        }
                    }
                    is LoadState.NotLoading -> {}
                }
            }
        }
    }
}

@Composable
fun ShowErrorItem(e: LoadState.Error) {
    val errorMessage = ApiErrorHandling.handleError(e.error).error
    Toast.makeText(LocalContext.current, errorMessage.toUi(), Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }.show()
}

@Composable
fun WorkerItem(
    item: OompaLoompaUI,
    onItemClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item.id) }
            .padding(vertical = 8.dp)
            .border(
                border = ButtonDefaults.outlinedButtonBorder, shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        WorkerImage(modifier = Modifier.padding(8.dp), imageUrl = item.image)

        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WorkerTitle(item.completeName, item.country, item.gender)

            ProfessionChip(item.profession)

        }
    }
}

@Composable
fun WorkerTitle(completeName: Pair<String, String>, country: String, gender: String) {
    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${completeName.first} ${completeName.second}",
            textAlign = TextAlign.Center,
            style = AppTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.alpha(0.7F),
            text = "$country · " + stringResource(id = gender.toGenderName()),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.titleSmall
        )
    }
}


@Composable
fun WorkerImage(
    imageUrl: String, modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}

@CombinedPreviews
@Composable
private fun HomeScreenPreview() {
    WillyWonkaChocolateFactoryTheme {
        WorkerItem(item = OompaLoompaUI(
            gender = "M",
            id = 1,
            image = "https://i.pinimg.com/736x/92/30/24/92302412968f3769ba6f1450ea7f52ff.jpg",
            age = 40,
            country = "Gengenbach",
            email = "boss@chocolatetruffl.es ",
            favorite = OompaLoompaUI.FavoriteUI(
                color = "Red",
                food = "Donut",
                random_string = "loremipsum",
                song = "Lalalala",
            ),
            completeName = Pair("Willy", "Wonka"),
            height = 120,
            profession = "Brewer",

            ), onItemClick = {})
    }
}