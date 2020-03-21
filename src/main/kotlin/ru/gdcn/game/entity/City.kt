package ru.gdcn.game.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import kotlin.jvm.Transient

@Entity
@Table(name = "t_city")
class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @NotNull
    @NotBlank
    var name: String = ""

    @NotNull
    @Positive
    var costMask: Int = 1

    @NotNull
    @Positive
    var costVacc: Int = 1

    @Transient
    lateinit var players: MutableList<String>
}
