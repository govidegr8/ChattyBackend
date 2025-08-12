package com.example.chattybackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChattyBackendApplication

fun main(args: Array<String>) {
    runApplication<ChattyBackendApplication>(*args)
}
