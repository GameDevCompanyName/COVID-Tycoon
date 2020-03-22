package ru.gdcn.game.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import ru.gdcn.boot.entity.User
import ru.gdcn.boot.service.UserService
import ru.gdcn.game.api.Response
import ru.gdcn.game.api.ResponseStatus
import ru.gdcn.game.api.service.GameService
import ru.gdcn.game.entity.City
import ru.gdcn.game.entity.Player

import java.security.Principal

@Controller
class GameController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var gameService: GameService

    @GetMapping("/game/player")
    @ResponseBody
    fun getPlayer(principal: Principal): Response<Player> {
        val user = getUser(principal) ?: return Response(ResponseStatus.ERROR, null)
        val player = gameService.loadPlayerByUserId(user.id) ?: return Response(ResponseStatus.ERROR, null)
        return Response(ResponseStatus.OK, player)
    }

    @GetMapping("/game/city")
    @ResponseBody
    fun getCity(principal: Principal): Response<City> {
        val user = getUser(principal) ?: return Response(ResponseStatus.ERROR, null)
        val player = gameService.loadPlayerByUserId(user.id) ?: return Response(ResponseStatus.ERROR, null)
        val city = gameService.loadCityByCityId(player.cityId) ?: return Response(ResponseStatus.ERROR, null)
        city.players = gameService.loadPlayersNameByCityId(player.cityId)
        return Response(ResponseStatus.OK, city)
    }

    private fun getUser(principal: Principal): User? = try {
        userService.loadUserByUsername(principal.name) as User
    } catch (e: Exception) {
        null
    }
}