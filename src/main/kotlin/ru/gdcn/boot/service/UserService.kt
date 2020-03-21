package ru.gdcn.boot.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import ru.gdcn.boot.entity.Role
import ru.gdcn.boot.entity.RolesType
import ru.gdcn.boot.entity.User
import ru.gdcn.boot.repository.RoleRepository
import ru.gdcn.boot.repository.UserRepository


@Service
class UserService : UserDetailsService {
    @PersistenceContext
    private lateinit var em: EntityManager
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Throws(IllegalArgumentException::class, UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username == null) {
            throw IllegalArgumentException("Username can't be empty!")
        }

        val user = userRepository.findByUserName(username)

        if (user.isEmpty) {
            throw UsernameNotFoundException("User does not exist!")
        }

        return user.get()
    }

    fun saveUser(user: User): Boolean {
        val userFromDB = userRepository.findByUserName(user.username)
        if (!userFromDB.isEmpty) {
            return false
        }

        user.roles = mutableSetOf(Role(1L, RolesType.ROLE_USER))
        user.pass = bCryptPasswordEncoder.encode(user.password)
        userRepository.save(user)

        return true
    }
}
