package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.tab_name_events
import pickupconnpassevents.composeapp.generated.resources.tab_name_favorites

enum class Destination(
    val route: String,
    val labelRes: StringResource
) {
    Events("events", Res.string.tab_name_events),
    Favorites("favorites", Res.string.tab_name_favorites);
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val startDestination = Destination.Events
        var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

        Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
            Column {
                PrimaryTabRow(
                    selectedTabIndex = selectedDestination,
                    modifier = Modifier.padding(contentPadding)
                ) {
                    Destination.entries.forEachIndexed { index, destination ->
                        Tab(
                            selected = selectedDestination == index,
                            onClick = {
                                selectedDestination = index
                                navController.navigate(
                                    route = destination.route
                                )
                            },
                            text = { Text(stringResource(destination.labelRes)) }
                        )
                    }
                }
                AppNavHost(
                    navController = navController,
                    startDestination = startDestination,
                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.Events -> EventsScreen(modifier)
                    Destination.Favorites -> FavoritesScreen(modifier)
                }
            }
        }
    }
}

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Events Screenだよ")
    }
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Favorites Screenだよ")
    }
}


