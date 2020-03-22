package ru.gdcn.game.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import ru.gdcn.game.api.repository.CityRepository
import ru.gdcn.game.api.repository.PlayerRepository

@Component
class ServiceHelper {
    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Autowired
    lateinit var cityRepository: CityRepository
}