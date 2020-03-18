package ru.gdcn.boot.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.gdcn.boot.entity.User
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(userName: String): Optional<User>
}