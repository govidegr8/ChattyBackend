package com.example.chattybackend.controller

import com.example.chattybackend.model.ChatMessage
import com.example.chattybackend.repository.ChatMessageRepository
import com.example.chattybackend.service.ChatSessionService
import com.example.chattybackend.service.UserService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val chatMessageRepository: ChatMessageRepository,
    private val userService: UserService,
    private val chatSessionService: ChatSessionService
) {

    @MessageMapping("/chat/{sessionId}/sendMessage")
    fun sendMessage(@DestinationVariable sessionId: Long, @Payload chatMessageDto: ChatMessageDto) {
        val user = userService.findByUsername(chatMessageDto.sender)
        val session = chatSessionService.findById(sessionId)

        if (user != null && session != null) {
            val chatMessage = ChatMessage(
                sender = user,
                content = chatMessageDto.content,
                timestamp = LocalDateTime.now(),
                chatSession = session
            )
            println("${chatMessage.toString()}--------------------->")
            chatMessageRepository.save(chatMessage)
            simpMessagingTemplate.convertAndSend("/topic/sessions/$sessionId", chatMessage)
        }
    }
}

data class ChatMessageDto(val sender: String, val content: String)