package ru.yandex.practicum.sprint10koh13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yandex.practicum.sprint10koh13.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    companion object {
        const val MY_USER_ID = "1"
        const val MY_FRIEND_USER_ID = "2"
    }

    private val chatItems = mutableListOf<ChatItem>()
    private val chatItemsAdapter = ChatItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//            val radiusInDp: Int = 2 * resources.displayMetrics.density
        val radiusInDp: Float = 2f
        val radiusInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            radiusInDp,
            resources.displayMetrics
        )
        val radiusInPx2 = resources.getDimension(R.dimen.corner_radius)

//            .transformation(RoundedCorners(radius))

        chatItems.add(
            ChatItem(
                text = "Hi",
                date = Date(),
                status = DeliveryStatus.READ,
                userId = MY_USER_ID
            )
        )

        binding.chatMessages.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL, true
            )
            adapter = chatItemsAdapter
        }

        chatItemsAdapter.updateItems(chatItems)


        binding.inputText.doAfterTextChanged {
            binding.footerAction.setImageResource(
                if (it.isNullOrBlank()) {
                    R.drawable.ic_audio
                } else {
                    R.drawable.ic_send
                }
            )
        }

        binding.footerAction.setOnClickListener {
            val newMessageText = binding.inputText.text?.toString()
            if (!newMessageText.isNullOrBlank()) {
                val newMessage = ChatItem(
                    text = newMessageText,
                    date = Date(),
                    status = DeliveryStatus.SENT,
                    userId = MY_USER_ID
                )
                chatItems.add(0, newMessage)
                binding.inputText.setText("")
                chatItemsAdapter.updateItems(chatItems)

                binding.root.postDelayed({
                    val newMessageFromFriend = ChatItem(
                        text = "Ok, $newMessageText",
                        date = Date(),
                        status = DeliveryStatus.SENT,
                        userId = MY_FRIEND_USER_ID
                    )
                    chatItems.add(0, newMessageFromFriend)
                    chatItemsAdapter.updateItems(chatItems)
                }, 2000)
            }
        }
    }

}