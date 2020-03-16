package ru.gdcn.app

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Controller {

    @GetMapping("/")
    fun blog(): String {
        return "index"
    }

}