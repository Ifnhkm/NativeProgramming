package com.example.movies.data
import com.example.movies.R
import com.example.movies.model.Movies

class Datasource() {
    fun loadMovies(): List<Movies> {
        return listOf<Movies>(
            Movies(R.string.movie1, R.string.title1, R.string.day1, R.drawable.a1),
            Movies(R.string.movie2, R.string.title2, R.string.day2, R.drawable.a2),
            Movies(R.string.movie3, R.string.title3, R.string.day3, R.drawable.a3),
            Movies(R.string.movie4, R.string.title4, R.string.day4, R.drawable.a4),
            Movies(R.string.movie5, R.string.title5, R.string.day5, R.drawable.a5),
            Movies(R.string.movie6, R.string.title6, R.string.day6, R.drawable.a6),
            Movies(R.string.movie7, R.string.title7, R.string.day7, R.drawable.a7),
            Movies(R.string.movie8, R.string.title8, R.string.day8, R.drawable.a8),
            Movies(R.string.movie9, R.string.title9, R.string.day9, R.drawable.a9),
            Movies(R.string.movie10, R.string.title10, R.string.day10, R.drawable.a10))
    }
}