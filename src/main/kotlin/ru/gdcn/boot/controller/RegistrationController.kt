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
import ru.gdcn.game.api.service.GameService
import ru.gdcn.game.entity.Player
import java.lang.Exception
import java.security.Principal


@Controller
class RegistrationController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var gameService: GameService

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

        val userIdFromDB = userService.saveUser(userForm)
        if (userIdFromDB.isEmpty){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует")
            return "registration"
        }

        return try {
            val player = Player.createNewDefaultPlayer(userIdFromDB.get(), userForm.username)
            if (gameService.savePlayer(player)) {
                "redirect:/"
            } else {
                userService.deleteUserById(userIdFromDB.get())
                "registration"
            }
        } catch (e: Exception) {
            userService.deleteUserById(userIdFromDB.get())
            "registration"
        }
    }

    @GetMapping("/")
    @ResponseBody
    fun home(principal: Principal): String {
        return try {
            return ObjectMapper().writeValueAsString(userService.loadUserByUsername(principal.name) as User)
        } catch (e: IllegalArgumentException) {
            "Введено некорретное имя!"
        } catch (e: UsernameNotFoundException) {
            "Пользователя не существует!"
        }
    }
}
