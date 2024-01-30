package ru.yandex.practicum.sprint10koh13

import java.util.Date
import java.util.UUID

data class ChatItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val date: Date,
    val status: DeliveryStatus,
    val userId: String,
)

enum class DeliveryStatus {
    SENT,
    RECEIVED,
    READ,
}