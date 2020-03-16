package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication
open class ServerMain {
    fun main (args: String) {
        SpringApplication.run(ServerMain::class.java, args)
    }
}