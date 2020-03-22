package ru.gdcn.game.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

import ru.gdcn.game.api.Response
import ru.gdcn.game.api.ResponseStatus
import ru.gdcn.game.entity.City
import ru.gdcn.game.entity.Player

import java.security.Principal

@Controller
class GameController {
    @Autowired
    private lateinit var controllerHelper: ControllerHelper

    @GetMapping("/game/player")
    @ResponseBody
    fun getPlayer(principal: Principal): Response<Player> {
        val player = controllerHelper.getPlayerByPrincipal(principal)
        return if (player.isEmpty) {
            Response(ResponseStatus.ERROR, null)
        } else {
            Response(ResponseStatus.OK, player.get())
        }
    }

    @GetMapping("/game/city")
    @ResponseBody
    fun getCity(principal: Principal): Response<City> {
        val player = controllerHelper.getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        val city = controllerHelper.gameService.loadCityByCityId(player.get().cityId)
        if (city.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        city.get().players = controllerHelper.gameService.loadPlayersNameByCityId(player.get().cityId)
        return Response(ResponseStatus.OK, city.get())
    }

    @PostMapping("/game/randomMove")
    @ResponseBody
    fun randomMoveToNewCity(principal: Principal): Response<String> {
        val player = controllerHelper.getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        val dataMove = controllerHelper.gameService.movePlayerToRandomCity(player.get())
        if (dataMove.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        return Response(ResponseStatus.OK, dataMove.get())
    }
}