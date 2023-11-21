package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.data.Datasource
import com.example.movies.model.Movies
import com.example.movies.ui.theme.MoviesTheme

/* Name : Muhammad Irfan Hakim bin Saharul Redzuan
   Date : 18/11/2023
   Program : Computer Science (Mobile Computing)
*/

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesApp()
                }
            }
        }
    }
}

@Composable
fun MoviesApp() {
    MovieList(
        MovieList = Datasource().loadMovies(),
    )
}

@Composable
fun MovieList(MovieList: List<Movies>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(MovieList) { movies ->
            MovieCard(
                movies = movies,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun MovieCard(movies: Movies, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column {
            Text(
                text = LocalContext.current.getString(movies.day),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = LocalContext.current.getString(movies.title),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            // Integrate the Box for expansion
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
                    .height(if (expanded) 400.dp else 200.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }
            ) {
                // Column layout for stacking Image and Text vertically
                Column {
                    // Image with clickable modifier
                    Image(
                        painter = painterResource(movies.imageResourceId),
                        contentDescription = stringResource(movies.stringResourceId),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(194.dp)
                            .clickable {
                                expanded = !expanded
                            },
                        contentScale = ContentScale.Crop
                    )

                    // Description text with conditional visibility
                    if (expanded) {
                        Text(
                            text = LocalContext.current.getString(movies.stringResourceId),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun MoviesCardPreview() {
    MovieCard(Movies(R.string.movie1, R.string.title1, R.string.day1, R.drawable.a1))
}