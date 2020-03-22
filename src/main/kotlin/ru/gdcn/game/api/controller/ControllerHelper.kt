package ru.gdcn.game.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import ru.gdcn.boot.entity.User
import ru.gdcn.boot.service.UserService
import ru.gdcn.game.api.service.GameService
import ru.gdcn.game.api.service.ResourcesService
import ru.gdcn.game.entity.Player

import java.security.Principal
import java.util.*

@Component
class ControllerHelper {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var gameService: GameService

    @Autowired
    lateinit var resourcesService: ResourcesService

    fun getPlayerByPrincipal(principal: Principal): Optional<Player> {
        val user = getUserByPrincipal(principal)
        if (user.isEmpty) {
            return Optional.empty()
        }
        return gameService.loadPlayerByUserId(user.get().id)
    }

    private fun getUserByPrincipal(principal: Principal): Optional<User> = try {
        Optional.of(userService.loadUserByUsername(principal.name) as User)
    } catch (e: Exception) {
        Optional.empty()
    }
}