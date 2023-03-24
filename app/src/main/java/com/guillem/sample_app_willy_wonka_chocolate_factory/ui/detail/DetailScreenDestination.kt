package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.guillem.sample_app_willy_wonka_chocolate_factory.R
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components.AppTopBar
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components.ExpandableText
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components.ProfessionChip
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.WillyWonkaChocolateFactoryTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.providers.AppTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.tooling.CombinedPreviews
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.Destination
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.DestinationDeclaration
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.getWith
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.utils.collectWithLifecycle
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.utils.toGenderName
import org.koin.core.scope.Scope

private const val IMAGE_RATIO_WIDTH = 100
private const val IMAGE_RATIO_HEIGHT = 100

class DetailScreenDestination(
    val workerId: Int
) : Destination<Unit>() {
    @Composable
    override fun Content(navEntry: NavBackStackEntry, diScope: Scope, outerArgs: Unit) {
        DetailScreen(diScope.getWith(workerId))
    }

    companion object : DestinationDeclaration<DetailScreenDestination>(
        DetailScreenDestination::class
    )
}

@Composable
fun DetailScreen(detailViewModel: DetailScreenViewModel) {
    val workerData by detailViewModel.workerData.collectWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(detailViewModel.failureData) {
        detailViewModel.failureData.collect { message ->
            message?.let {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                }.show()
            }
        }
    }

    DetailScreenContent(
        workerData = workerData, onBackButtonClicked = detailViewModel::onBackButtonClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreenContent(
    workerData: OompaLoompaUI, onBackButtonClicked: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(topBar = {
            AppTopBar(navigationIcon = {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = AppTheme.colors.onSurface,
                        contentDescription = null
                    )
                }
            })
        }) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WorkerDetailImage(workerData.image)

                WorkerHeader(
                    workerData.completeName,
                    workerData.country,
                    workerData.gender,
                    workerData.profession,
                )

                workerData.description?.let { description ->
                    ItemColumn(
                        title = R.string.description, text = description
                    )
                }

                listOf(
                    Pair(stringResource(id = R.string.email), workerData.email),
                    Pair(stringResource(id = R.string.age), "${workerData.age} years"),
                    Pair(stringResource(id = R.string.height), "${workerData.height} cm"),
                    Pair(stringResource(id = R.string.fav_color), workerData.favorite.color),
                    Pair(stringResource(id = R.string.fav_food), workerData.favorite.food),
                ).forEach { item ->
                    ItemRowSpecs(title = item.first, value = item.second)
                }
                ItemColumn(title = R.string.fav_song, text = workerData.favorite.song)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
            }
        }
    }
}

@Composable
fun WorkerDetailImage(image: String) {
    Box {
        val imageWidth = LocalConfiguration.current.screenWidthDp.dp - (AppTheme.appPadding)
        val imageHeight = imageWidth * IMAGE_RATIO_WIDTH / IMAGE_RATIO_HEIGHT
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(imageWidth)
                .height(imageHeight),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun WorkerHeader(
    completeName: Pair<String, String>?, country: String?, gender: String?, profession: String?
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${completeName?.first} ${completeName?.second}",
            style = AppTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Text(
            textAlign = TextAlign.Center,
            text = "$country · " + stringResource(id = gender.toGenderName())
        )

        ProfessionChip(profession ?: stringResource(id = R.string.unknown))
    }
}

@Composable
fun ItemColumn(
    title: Int,
    text: String,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray.copy(alpha = 0.1F)),
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold
        )
        ExpandableText(
            text = text, modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        )
    }
}

@Composable
private fun ItemRowSpecs(
    title: String,
    value: String,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray.copy(alpha = 0.1F)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .weight(2F)
                .padding(8.dp), textAlign = TextAlign.Center, text = value
        )
    }
}

@CombinedPreviews
@Composable
private fun DetailScreenPreview() {
    WillyWonkaChocolateFactoryTheme {
        DetailScreenContent(workerData = OompaLoompaUI(
            gender = "M",
            id = 1,
            image = "https://i.pinimg.com/736x/92/30/24/92302412968f3769ba6f1450ea7f52ff.jpg",
            age = 40,
            country = "Gengenbach",
            email = "boss@chocolatetruffl.es",
            favorite = OompaLoompaUI.FavoriteUI(
                color = "Red",
                food = "Donut",
                random_string = "loremipsum",
                song = "Lalalala",
            ),
            completeName = Pair("Willy", "Wonka"),
            height = 120,
            profession = "Brewer",
        ), onBackButtonClicked = {})
    }
}