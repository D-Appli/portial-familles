package fr.dappli.portailfamilles.core.kotlin.mapper

interface Mapper<T, R> {
    fun map(param: T): R
}
