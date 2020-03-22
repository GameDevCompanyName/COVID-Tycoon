package ru.gdcn.boot.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Component
@javax.persistence.Entity
@javax.persistence.Table(name = "t_user")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @NotNull
    @NotBlank
    var userName: String = ""

    @NotNull
    @NotBlank
    var pass: String = ""

    @Transient
    var passConfirm: String = ""

    @NotNull
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
