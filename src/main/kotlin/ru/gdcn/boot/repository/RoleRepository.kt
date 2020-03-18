package ru.gdcn.boot.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.gdcn.boot.entity.Role

interface RoleRepository : JpaRepository<Role, Long> {

}