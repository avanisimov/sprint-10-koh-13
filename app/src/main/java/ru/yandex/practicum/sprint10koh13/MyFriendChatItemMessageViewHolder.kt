package ru.yandex.practicum.sprint10koh13

import androidx.recyclerview.widget.RecyclerView
import ru.yandex.practicum.sprint10koh13.databinding.MyChatItemMessageBinding
import ru.yandex.practicum.sprint10koh13.databinding.MyFriendChatItemMessageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MyFriendChatItemMessageViewHolder(
    private val binding: MyFriendChatItemMessageBinding,
) : MyChatItemViewHolder(binding.root) {

    private val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun bind(item: ChatItem) {
        binding.message.text = item.text
        binding.date.text = dateFormatter.format(item.date)
    }

}