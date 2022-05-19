package com.example.myanimelist.service.html

interface IHTMLGenerator<in T> {
    fun generate(dto: T)
}