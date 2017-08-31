package com.demo.controller

import com.demo.exceptions.Error
import com.demo.exceptions.UserNotFoundException
import com.demo.model.User

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController {

    val users = mutableListOf(User("milos", 22), User("nemanja", 23))

    @GetMapping("/")
    fun findAll() = users

    @GetMapping("/{name}")
    fun findByUsername(@PathVariable name: String) =
            users.find { it.username == name } ?: throw UserNotFoundException(name)

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFound(ex: UserNotFoundException) =
            ResponseEntity(Error(404, "User ${ex.name} not found!"), HttpStatus.NOT_FOUND)

}