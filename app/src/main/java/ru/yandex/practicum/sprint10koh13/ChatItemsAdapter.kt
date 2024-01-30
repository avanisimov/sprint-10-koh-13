package ru.yandex.practicum.sprint10koh13

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.yandex.practicum.sprint10koh13.databinding.MyChatItemMessageBinding
import ru.yandex.practicum.sprint10koh13.databinding.MyFriendChatItemMessageBinding

class ChatItemsAdapter : RecyclerView.Adapter<MyChatItemViewHolder>() {

    private var items = emptyList<ChatItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatItemViewHolder {
        return if (viewType == VIEW_TYPE_MY_CHAT_ITEM_MESSAGE) {
            val binding =
                MyChatItemMessageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            MyChatItemMessageViewHolder(binding)
        } else {
            val binding =
                MyFriendChatItemMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            MyFriendChatItemMessageViewHolder(binding)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].userId == MainActivity.MY_USER_ID) {
            VIEW_TYPE_MY_CHAT_ITEM_MESSAGE
        } else {
            VIEW_TYPE_MY_FRIEND_CHAT_ITEM_MESSAGE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyChatItemViewHolder, position: Int) {
        Log.d("SPRINT_10_or_9", "onBindViewHolder position=$position")
        holder.bind(items[position])
    }

    fun updateItems(chatItems: MutableList<ChatItem>) {
        val oldItems = items
        items = chatItems.toMutableList()
        val newItems = chatItems
        val diffResult = DiffUtil.calculateDiff(object: DiffUtil.Callback() {

            override fun getOldListSize(): Int {
                return oldItems.size
            }

            override fun getNewListSize(): Int {
               return newItems.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }

        })
        diffResult.dispatchUpdatesTo(this)

    }

    companion object {
        const val VIEW_TYPE_MY_CHAT_ITEM_MESSAGE = 1
        const val VIEW_TYPE_MY_FRIEND_CHAT_ITEM_MESSAGE = 2
        const val VIEW_TYPE_CHAT_ITEM_DATE_DIVIDER = 3
    }
}

abstract class MyChatItemViewHolder(
    itemView: View
) : ViewHolder(itemView) {

    abstract fun bind(item: ChatItem)
}