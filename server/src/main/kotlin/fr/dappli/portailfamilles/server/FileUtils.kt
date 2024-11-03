package fr.dappli.portailfamilles.server

fun readResourceFile(relativeResourcePath: String): String =
    Thread.currentThread().contextClassLoader
        ?.getResourceAsStream(relativeResourcePath)
        ?.bufferedReader()?.use { it.readText() } ?: ""
