package ru.gdcn.boot.entity

import org.hibernate.annotations.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient
import javax.validation.constraints.Size
import javax.persistence.FetchType
import javax.persistence.ManyToMany

@javax.persistence.Entity
@javax.persistence.Table(name = "t_user")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    @Size(min=2, message = "Не меньше 5 знаков")
    var userName: String = ""
    @Size(min=2, message = "Не меньше 5 знаков")
    var pass: String = ""
    @Transient
    var passConfirm: String = ""
    @ManyToMany(fetch = FetchType.EAGER)
    var roles: MutableSet<Role> = mutableSetOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = userName

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = pass

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}