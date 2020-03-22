package ru.gdcn.boot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

import ru.gdcn.boot.entity.User
import ru.gdcn.boot.service.UserService

import javax.validation.Valid
import org.springframework.web.bind.annotation.ModelAttribute
import java.security.Principal


@Controller
class RegistrationController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/login")
    fun login(principal: Principal?): String {
        if (principal != null) {
            return "redirect:/"
        }
        return "login"
    }

    @GetMapping("/registration")
    fun registration(principal: Principal?, model: Model): String {
        if (principal != null) {
            return "redirect:/"
        }
        model.addAttribute("userForm", User())
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(@ModelAttribute("userForm") @Valid userForm: User, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "registration"
        }

        if (userForm.password != userForm.passConfirm){
            model.addAttribute("passwordError", "Пароли не совпадают")
            return "registration"
        }

        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует")
            return "registration"
        }

        return "redirect:/"
    }

    @GetMapping("/")
    fun home(principal: Principal): String = "index"

}
