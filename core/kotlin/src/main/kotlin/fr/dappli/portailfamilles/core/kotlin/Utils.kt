package fr.dappli.portailfamilles.core.kotlin

fun String.titleCase(): String = lowercase().trim().replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}
