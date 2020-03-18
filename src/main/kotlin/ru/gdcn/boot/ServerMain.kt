package ru.gdcn.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

@SpringBootApplication
open class ServerMain

fun main(args: Array<String>) {
    SpringApplication.run(ServerMain::class.java, *args)

}