package com.example.chattybackend.service

import com.example.chattybackend.model.ChatSession
import com.example.chattybackend.model.User
import com.example.chattybackend.repository.ChatSessionRepository
import org.springframework.stereotype.Service

@Service
class ChatSessionService(private val chatSessionRepository: ChatSessionRepository) {

    fun createSession(name: String): ChatSession {
        val newSession = ChatSession(name = name)
        return chatSessionRepository.save(newSession)
    }

    fun findById(sessionId: Long): ChatSession? {
        return chatSessionRepository.findById(sessionId).orElse(null)
    }

    fun addUserToSession(session: ChatSession, user: User) {
        session.users.add(user)
        chatSessionRepository.save(session)
    }

    fun getAllSessions(): List<ChatSession> {
        return chatSessionRepository.findAll()
    }
}