package ru.gdcn.boot.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry

@Configuration
open class MvcConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
//        registry.addViewController("/login").setViewName("login")
    }
}
