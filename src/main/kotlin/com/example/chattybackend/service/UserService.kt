package com.example.chattybackend.service

import com.example.chattybackend.model.User
import com.example.chattybackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}