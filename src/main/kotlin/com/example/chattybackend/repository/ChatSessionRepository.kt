package com.example.chattybackend.repository

import com.example.chattybackend.model.ChatSession
import org.springframework.data.jpa.repository.JpaRepository

interface ChatSessionRepository : JpaRepository<ChatSession, Long>