package com.example.players.service

import org.springframework.stereotype.Service

interface Reward {
    val name : String
    val amout  : Int
    fun reward(): Int
}

interface GoldReward : Reward

interface RewardFactory {
    fun getInstance(name : String) : Reward
}

class GoldRewardFactory : RewardFactory {
    override fun getInstance(name: String) : GoldReward {
        return when (name){
            "QuestBoss" -> QuestBoss()
            else -> throw IllegalArgumentException("Нет класса $name")
        }
    }

    private class QuestBoss : GoldReward {
        override val name: String = "QuestBoss"

        override val amout: Int = (50..200).random()

        override fun reward(): Int {
            var srt: Int = amout
            return srt
        }

    }
}

@Service
class PlayerRewardService {
    fun createReward(factory: RewardFactory, name : String) : Reward {
        return factory.getInstance(name)
    }

    val goldFactory = GoldRewardFactory()

    fun getGoldQuestBoss(): Int {
        var userReward = createReward(goldFactory, "QuestBoss")
        userReward.reward()

        return userReward.reward()
    }

    fun getHello(): String {
        val str: String = "Hello service!"
        return str
    }
}

@Service
class PlayerRewardServiceNames {
    fun createReward(factory: RewardFactory, name : String) : Reward {
        return factory.getInstance(name)
    }

    val goldFactory = GoldRewardFactory()

    fun getGoldQuestBoss(): Int {
        var userReward = createReward(goldFactory, "QuestBoss")
        userReward.reward()

        return userReward.reward()
    }

    // Получаем строку имён, распарсиваем её и присваиваем награду
    fun getPlayer(name: String): List<Any> {
        var list = mutableListOf<Any>() // Создаём пустой изменяемый ArrayList

        val array = name.split(",").toTypedArray() // Разбиваем строку на массив
        array.forEach {
            var userReward = createReward(goldFactory, "QuestBoss") // Генерируем награду
            val json = PlayerResponse(
                name = it,
                reward = "Gold",
                amount = userReward.reward()
            ).toString()
            list.add(json)
        }
        return list
    }
}

data class PlayerResponse (
    val name: String,
    val reward: String,
    val amount: Int
)
