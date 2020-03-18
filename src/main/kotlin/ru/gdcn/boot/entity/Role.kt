package ru.gdcn.boot.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity

import javax.persistence.Id
import javax.persistence.ManyToMany


@Entity
@javax.persistence.Table(name = "t_role")
class Role() : GrantedAuthority {
    @Id
    var id: Long = -1
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