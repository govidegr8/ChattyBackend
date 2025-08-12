package com.example.chattybackend.repository

import com.example.chattybackend.model.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage, Long>