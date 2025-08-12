package com.example.chattybackend.controller

import com.example.chattybackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val user = userService.findByUsername(loginRequest.username)
        return if (user != null && user.passwordHash == loginRequest.password) {
            ResponseEntity.ok("Login successful")
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
        }
    }
}

data class LoginRequest(val username: String, val password: String)