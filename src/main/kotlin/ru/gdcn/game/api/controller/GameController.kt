package ru.gdcn.game.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

import ru.gdcn.boot.entity.User
import ru.gdcn.boot.service.UserService
import ru.gdcn.game.api.Response
import ru.gdcn.game.api.ResponseStatus
import ru.gdcn.game.api.service.GameService
import ru.gdcn.game.entity.City
import ru.gdcn.game.entity.Player

import java.security.Principal
import java.util.*

@Controller
class GameController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var gameService: GameService

    @GetMapping("/game/player")
    @ResponseBody
    fun getPlayer(principal: Principal): Response<Player> {
        val player = getPlayerByPrincipal(principal)
        return if (player.isEmpty) {
            Response(ResponseStatus.ERROR, null)
        } else {
            Response(ResponseStatus.OK, player.get())
        }
    }

    @GetMapping("/game/city")
    @ResponseBody
    fun getCity(principal: Principal): Response<City> {
        val player = getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        val city = gameService.loadCityByCityId(player.get().cityId)
        if (city.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        city.get().players = gameService.loadPlayersNameByCityId(player.get().cityId)
        return Response(ResponseStatus.OK, city.get())
    }

    @PostMapping("/game/randomMove")
    @ResponseBody
    fun randomMoveToNewCity(principal: Principal): Response<String> {
        val player = getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        val dataMove = gameService.movePlayerToRandomCity(player.get())
        if (dataMove.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        return Response(ResponseStatus.OK, dataMove.get())
    }

    private fun getUserByPrincipal(principal: Principal): Optional<User> = try {
        Optional.of(userService.loadUserByUsername(principal.name) as User)
    } catch (e: Exception) {
        Optional.empty()
    }

    private fun getPlayerByPrincipal(principal: Principal): Optional<Player> {
        val user = getUserByPrincipal(principal)
        if (user.isEmpty) {
            return Optional.empty()
        }
        return gameService.loadPlayerByUserId(user.get().id)
    }
}