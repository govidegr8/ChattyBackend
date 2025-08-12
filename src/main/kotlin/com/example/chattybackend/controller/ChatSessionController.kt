package com.example.chattybackend.controller

import com.example.chattybackend.model.ChatSession
import com.example.chattybackend.service.ChatSessionService
import com.example.chattybackend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sessions")
class ChatSessionController(
    private val chatSessionService: ChatSessionService,
    private val userService: UserService
) {

    @PostMapping
    fun createSession(@RequestBody createSessionRequest: CreateSessionRequest): ChatSession {
        return chatSessionService.createSession(createSessionRequest.name)
    }

    @GetMapping
    fun getAllSessions(): List<ChatSession> {
        return chatSessionService.getAllSessions()
    }

    @PostMapping("/{sessionId}/join")
    fun joinSession(@PathVariable sessionId: Long, @RequestBody joinSessionRequest: JoinSessionRequest): ResponseEntity<String> {
        val session = chatSessionService.findById(sessionId)
        val user = userService.findByUsername(joinSessionRequest.username)

        if (session != null && user != null) {
            chatSessionService.addUserToSession(session, user)
            return ResponseEntity.ok("User joined session")
        }
        return ResponseEntity.badRequest().body("Session or user not found")
    }
}

data class CreateSessionRequest(val name: String)
data class JoinSessionRequest(val username: String)