package com.example.twitter.storage

interface Crud<E> {

    fun create(tweetItem: E)

    fun read(): MutableList<E>

    fun update(tweetItem: E, index: Int)

    fun delete(index: Int)

}