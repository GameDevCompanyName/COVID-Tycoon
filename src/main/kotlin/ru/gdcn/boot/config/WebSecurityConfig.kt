package ru.gdcn.boot.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.session.HttpSessionEventPublisher

import ru.gdcn.boot.service.UserService

@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userService: UserService

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .sessionManagement().maximumSessions(1)
            .expiredUrl("/")
            .and().invalidSessionUrl("/")
            .sessionFixation().newSession()

        httpSecurity
            .authorizeRequests()
            //Доступ только для не зарегистрированных пользователей
            .antMatchers("/registration").permitAll()
            //Все остальные страницы требуют аутентификации
            .anyRequest().authenticated()

        httpSecurity
            .formLogin()
            .permitAll()
            .and()
            .logout()
            .permitAll()
    }

    @Autowired
    @Throws(Exception::class)
    protected fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    open fun httpSessionEventPublisher(): HttpSessionEventPublisher {
        return HttpSessionEventPublisher()
    }

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
