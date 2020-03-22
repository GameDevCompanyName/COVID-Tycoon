package ru.gdcn.game.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import ru.gdcn.game.api.Response
import ru.gdcn.game.api.ResponseStatus
import ru.gdcn.game.api.repository.CityRepository
import ru.gdcn.game.api.repository.PlayerRepository
import ru.gdcn.game.entity.Player

@Service
class ResourcesService {

    @Autowired
    private lateinit var serviceHelper: ServiceHelper

    private val cityRepository: CityRepository by lazy { serviceHelper.cityRepository }
    private val playerRepository: PlayerRepository by lazy { serviceHelper.playerRepository }

    fun buyResource(player: Player, resId: Long, quantityRes: Int): Response<Player> {
        val city = cityRepository.findById(player.cityId)
        if (city.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        when (resId) {
            1L -> {
                val sumDeal = quantityRes*city.get().costMask
                if (player.money < sumDeal) {
                    return Response(ResponseStatus.ERROR_NO_MONEY, null)
                }
                player.money -= sumDeal
                player.quantityMask += quantityRes
                return try {
                    Response(ResponseStatus.OK, playerRepository.save(player))
                } catch (e: Exception) {
                    Response(ResponseStatus.ERROR, null)
                }
            }
            2L -> {
                val sumDeal = quantityRes*city.get().costVacc
                if (player.money < sumDeal) {
                    return Response(ResponseStatus.ERROR_NO_MONEY, null)
                }
                player.money -= sumDeal
                player.quantityVacc += quantityRes
                return try {
                    Response(ResponseStatus.OK, playerRepository.save(player))
                } catch (e: Exception) {
                    Response(ResponseStatus.ERROR, null)
                }
            }
            else -> return Response(ResponseStatus.ERROR, null)
        }
    }

    fun sellResource(player: Player, resId: Long, quantityRes: Int): Response<Player> {
        val city = cityRepository.findById(player.cityId)
        if (city.isEmpty) {
            return Response(ResponseStatus.ERROR, null)
        }

        when (resId) {
            1L -> {
                if (player.quantityMask < quantityRes) {
                    return Response(ResponseStatus.ERROR_NO_RESOURCE, null)
                }
                player.money += city.get().costMask * quantityRes
                player.quantityMask -= quantityRes
                return try {
                    Response(ResponseStatus.OK, playerRepository.save(player))
                } catch (e: Exception) {
                    Response(ResponseStatus.ERROR, null)
                }
            }
            2L -> {
                if (player.quantityVacc < quantityRes) {
                    return Response(ResponseStatus.ERROR_NO_RESOURCE, null)
                }
                player.money += city.get().costVacc * quantityRes
                player.quantityVacc -= quantityRes
                return try {
                    Response(ResponseStatus.OK, playerRepository.save(player))
                } catch (e: Exception) {
                    Response(ResponseStatus.ERROR, null)
                }
            }
            else -> return Response(ResponseStatus.ERROR, null)
        }
    }
}