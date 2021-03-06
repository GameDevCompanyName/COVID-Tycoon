package ru.gdcn.game.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "t_player")
class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @NotNull
    @NotBlank
    var name: String = ""

    @NotNull
    @PositiveOrZero
    var money: Int = 0

    @NotNull
    @JoinColumn(name = "city_id")
    var cityId: Long = -1

    @NotNull
    @PositiveOrZero
    var quantityMask: Int = 0

    @NotNull
    @PositiveOrZero
    var quantityVacc: Int = 0

    @NotNull
    @JoinColumn(name = "user_id")
    var userId: Long = -1

    companion object {
        fun createNewDefaultPlayer(userId: Long, userName: String): Player {
            val player = Player()
            player.name = userName
            player.userId = userId
            player.money = 100
            player.quantityMask = 1
            player.quantityVacc = 1
            player.cityId = 2
            return player
        }
    }
}
