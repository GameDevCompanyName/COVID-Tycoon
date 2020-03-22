package ru.gdcn.game.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.gdcn.game.entity.City

@Repository
interface CityRepository : JpaRepository<City, Long>