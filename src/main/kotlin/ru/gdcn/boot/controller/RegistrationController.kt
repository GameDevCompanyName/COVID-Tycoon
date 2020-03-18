package ru.gdcn.boot.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

import ru.gdcn.boot.entity.User
import ru.gdcn.boot.service.UserService

import javax.validation.Valid


@Controller
class RegistrationController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/registration")
    fun registration(model: Model): String {
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
}