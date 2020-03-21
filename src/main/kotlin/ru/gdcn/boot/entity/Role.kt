package ru.gdcn.boot.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import javax.persistence.Entity

import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Component
@Entity
@javax.persistence.Table(name = "t_role")
class Role() : GrantedAuthority {
    @Id
    var id: Long = -1

    @NotNull
    @NotBlank
    var name: String = "" //должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER

    @Transient
    @ManyToMany(mappedBy = "roles")
    var users: MutableSet<User> = mutableSetOf()

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }

    override fun getAuthority(): String = name
}