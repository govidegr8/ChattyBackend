package com.example.chattybackend.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ChatMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val sender: User,
    val content: String,
    val timestamp: LocalDateTime,
    @ManyToOne
    val chatSession: ChatSession
)