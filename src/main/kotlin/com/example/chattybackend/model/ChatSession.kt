package com.example.chattybackend.model

import jakarta.persistence.*

@Entity
data class ChatSession(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "session_users",
        joinColumns = [JoinColumn(name = "session_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var users: MutableSet<User> = mutableSetOf()
)