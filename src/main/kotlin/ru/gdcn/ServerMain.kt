package ru.gdcn

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    "ru.gdcn.game.*",
    "ru.gdcn.boot.*"
)
open class ServerMain

fun main(args: Array<String>) {
    SpringApplication.run(ServerMain::class.java, *args)

}