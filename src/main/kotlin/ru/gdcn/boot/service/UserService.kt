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

import ru.gdcn.boot.repository.RoleRepository
import ru.gdcn.boot.repository.UserRepository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
class UserService : UserDetailsService {
    @PersistenceContext
    lateinit var em: EntityManager
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails{
        if (username == null) {
            throw UsernameNotFoundException("User not found");
        }

        val user = userRepository.findByUserName(username)

        if (user.isEmpty) {
            throw UsernameNotFoundException("User not found");
        }

        return user.get()
    }

    fun findUserById(usrdId: Long): User {
        val user = userRepository.findById(usrdId)
        return user.orElse(User())
    }

    fun getAllUsers(): List<User> = userRepository.findAll()

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