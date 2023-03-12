package com.task.jetpackcomposeapp.utils

fun countStar(rating: Double): Int {
    return when (rating) {
        in 0.0..1.9 -> 1
        in 2.0..2.9 -> 2
        in 3.0..3.9 -> 3
        in 4.0..4.9 -> 4
        in 5.0..5.9 -> 5
        else -> 0
    }
}