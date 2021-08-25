package com.example.players.service

import org.springframework.stereotype.Service

interface Reward {
    val name : String
    val amout  : Int
    fun reward(): String
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

        override fun reward(): String {
            var srt: String = "Награда за убийство босса - $amout золотых"
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

    fun getHello(): String {
        var userReward = createReward(goldFactory, "QuestBoss")
        userReward.reward()

        return userReward.reward()

        //val str: String = "Hello service!"
        //return str
    }
}
