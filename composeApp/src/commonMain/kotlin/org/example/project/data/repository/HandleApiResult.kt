package org.example.project.data.repository

suspend fun <T> handleApiResult(func: suspend () -> Result<T>): Result<T> {
    return try {
        func()
    } catch (e: Exception) {
        Result.failure(e)
    }
}