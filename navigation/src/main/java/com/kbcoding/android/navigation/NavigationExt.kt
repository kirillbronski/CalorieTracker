package com.kbcoding.android.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import java.util.regex.Pattern
import kotlin.reflect.KClass

fun NavBackStackEntry?.routeClass(): KClass<*>? {
    return this?.destination.routeClass()
}

fun NavDestination?.routeClass(): KClass<*>? {
    return this?.route?.substringBefore("/")?.let { className ->
        generateSequence(className, ::replaceLastDotByDollar)
            .mapNotNull(::tryParseClass)
            .firstOrNull()
    }
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private val regexPattern = Pattern.compile("\\.([^.]+)\$")

private fun replaceLastDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if (index != -1) {
        regexPattern.matcher(input).replaceFirst("\\$$1")
    } else {
        null
    }
}