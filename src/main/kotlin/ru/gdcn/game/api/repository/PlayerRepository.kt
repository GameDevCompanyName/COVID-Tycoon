package ru.gdcn.game.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import java.util.*

import ru.gdcn.game.entity.Player

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun findByUserId(userId: Long): Optional<Player>
    fun findByCityId(cityId: Long): Optional<List<Player>>
    fun findByName(name: String): Optional<Player>
}
