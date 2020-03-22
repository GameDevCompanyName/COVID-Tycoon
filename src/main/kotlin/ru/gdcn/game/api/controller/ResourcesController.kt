package ru.gdcn.game.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import ru.gdcn.game.api.Response
import ru.gdcn.game.api.ResponseStatus
import ru.gdcn.game.api.service.ResourcesService
import ru.gdcn.game.entity.Player
import java.security.Principal

@Controller
class ResourcesController {
    @Autowired
    private lateinit var controllerHelper: ControllerHelper

    private val resourcesService: ResourcesService by lazy { controllerHelper.resourcesService }

    @PostMapping("/game/resources/buy")
    @ResponseBody
    fun buyResource(principal: Principal, @RequestParam resId: Long, @RequestParam quantityRes: Int): Response<Player> {
        val player = controllerHelper.getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        return resourcesService.buyResource(player.get(), resId, quantityRes)
    }

    @PostMapping("/game/resources/sell")
    @ResponseBody
    fun sellResource(principal: Principal, @RequestParam resId: Long, @RequestParam quantityRes: Int): Response<Player> {
        val player = controllerHelper.getPlayerByPrincipal(principal)
        if (player.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }
        return resourcesService.sellResource(player.get(), resId, quantityRes)
    }
}