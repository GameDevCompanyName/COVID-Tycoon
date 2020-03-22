package ru.gdcn.game.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.gdcn.game.api.repository.CityRepository

import ru.gdcn.game.api.repository.PlayerRepository
import ru.gdcn.game.entity.City
import ru.gdcn.game.entity.Player

import java.util.*

@Service
class GameService {
    @Autowired
    private lateinit var playerRepository: PlayerRepository

    @Autowired
    private lateinit var cityRepository: CityRepository

    fun loadPlayerByUserId(userId: Long): Optional<Player> = playerRepository.findByUserId(userId)

    fun loadPlayersNameByCityId(cityId: Long): MutableList<String> {
        val players = playerRepository.findByCityId(cityId)
        val result = mutableListOf<String>()
        for (p in players.get()) {
            result.add(p.name)
        }
        return result
    }

    fun loadCityByCityId(cityId: Long): Optional<City> = cityRepository.findById(cityId)
}
