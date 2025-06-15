package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.event.EventScreen
import org.example.project.ui.event.EventViewModel
import org.example.project.ui.favorite.FavoriteScreen
import org.example.project.ui.favorite.FavoriteViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import theming.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val startDestination = Destination.Events
        var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

        Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
            Column {
                PrimaryTabRow(
                    selectedTabIndex = selectedDestination,
                    indicator = {
                        TabRowDefaults.PrimaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(selectedDestination, matchContentSize = true),
                            color = AppTheme.colors.onPrimary,
                        )
                    },
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
                            text = {
                                Text(
                                    text = stringResource(destination.labelRes),
                                    style = AppTheme.typography.headlineMedium,
                                    color = AppTheme.colors.onPrimary,
                                )
                            }
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
                    Destination.Events -> {
                        val eventViewModel: EventViewModel = getKoin().get()
                        EventScreen(
                            viewModel = eventViewModel,
                            modifier = modifier,
                        )
                    }
                    Destination.Favorites -> {
                        val favoriteViewModel: FavoriteViewModel = getKoin().get()
                        FavoriteScreen(
                            viewModel = favoriteViewModel,
                            modifier =  modifier,
                        )
                    }
                }
            }
        }
    }
}
