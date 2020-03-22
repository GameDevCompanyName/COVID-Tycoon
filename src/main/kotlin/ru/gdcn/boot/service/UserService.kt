package ru.gdcn.boot.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import ru.gdcn.boot.entity.Role
import ru.gdcn.boot.entity.RolesType
import ru.gdcn.boot.entity.User
import ru.gdcn.boot.repository.UserRepository

import java.util.*


@Service
class UserService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

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

    fun saveUser(user: User): Optional<Long> {
        val userFromDB = userRepository.findByUserName(user.username)
        if (!userFromDB.isEmpty) {
            return Optional.empty()
        }

        user.roles = mutableSetOf(Role(1L, RolesType.ROLE_USER))
        user.pass = bCryptPasswordEncoder.encode(user.password)
        val newId = userRepository.save(user).id

        return Optional.of(newId)
    }

    fun deleteUserById(userId: Long) = userRepository.deleteById(userId)

}
