package ru.gdcn.game.api.service

import com.fasterxml.jackson.databind.ObjectMapper
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

    @Throws(Exception::class)
    fun savePlayer(player: Player): Boolean {
        val playerFromDB = playerRepository.findByName(player.name)
        if (!playerFromDB.isEmpty) {
            return false
        }
        playerRepository.save(player)
        return true
    }

    fun movePlayerToRandomCity(player: Player): Optional<String> {
        val cites = cityRepository.findAll()

        val updatePlayer: Player
        try {
            player.cityId = cites.random().id
            updatePlayer = playerRepository.save(player)
        } catch (e: Exception) {
            return Optional.empty()
        }

        val city = loadCityByCityId(updatePlayer.cityId)
        if (city.isEmpty) {
            return Optional.empty()
        }
        city.get().players = loadPlayersNameByCityId(city.get().id)

        val mapper = ObjectMapper()
        val statusForMove = mapper.createObjectNode()
        statusForMove.putPOJO("player", updatePlayer)
        statusForMove.putPOJO("city", city.get())

        return Optional.of(mapper.writeValueAsString(statusForMove))
    }
}
