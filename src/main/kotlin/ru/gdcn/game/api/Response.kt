package ru.gdcn.game.api

data class Response<ENTITY>(val status: Int, val entity: ENTITY?)
