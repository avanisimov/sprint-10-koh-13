package ru.yandex.practicum.sprint10koh13

import ru.yandex.practicum.sprint10koh13.databinding.MyChatItemMessageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MyChatItemMessageViewHolder(
    private val binding: MyChatItemMessageBinding,
) : MyChatItemViewHolder(binding.root) {

    private val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun bind(item: ChatItem) {
        binding.message.text = item.text
        binding.date.text = dateFormatter.format(item.date)
        binding.status.setImageResource(
            when (item.status) {
                DeliveryStatus.SENT -> R.drawable.ic_status_sent
                DeliveryStatus.RECEIVED -> R.drawable.ic_status_received
                DeliveryStatus.READ -> R.drawable.ic_status_read
            }
        )
    }

}