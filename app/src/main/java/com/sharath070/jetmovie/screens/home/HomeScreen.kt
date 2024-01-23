package com.sharath070.jetmovie.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sharath070.jetmovie.model.Movie
import com.sharath070.jetmovie.model.getMovies
import com.sharath070.jetmovie.navigation.MovieScreens
import com.sharath070.jetmovie.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Jet Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White),
                modifier = Modifier.shadow(elevation = 5.dp)
            )
        }
    ) {
        MainContent(
            paddingValues = it,
            navController = navController
        )
    }
}

@Composable
fun MainContent(
    movieList: List<Movie> = getMovies(),
    paddingValues: PaddingValues,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 12.dp)
    ) {
        LazyColumn {
            items(items = movieList) {
                val isFirstItem = movieList.indexOf(it) == 0
                MovieRow(it, isFirstItem) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}