package org.example.project.data.repository

import kotlin.jvm.JvmStatic

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(
        val error: Exception,
    ) : Result<T>()

    companion object {
        @JvmStatic
        fun <T> success(data: T): Result<T> = Success(data)

        @JvmStatic
        fun <T> failure(
            error: Exception,
        ): Result<T> = Failure(error)
    }
}