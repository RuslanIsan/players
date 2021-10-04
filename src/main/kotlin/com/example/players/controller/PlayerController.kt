package com.example.players.controller

import com.example.players.service.PlayerResponse
import com.example.players.service.PlayerRewardService
import com.example.players.service.PlayerRewardServiceNames
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class PlayerController(
    private var playerRewardService: PlayerRewardService,
    private var playerRewardServiceNames: PlayerRewardServiceNames
) {
    // Выводим Hello Services
    @GetMapping("helloWorld")
    fun getHelloWordMessage(): ResponseEntity<String> =
        ResponseEntity.ok(
            playerRewardService.getHello()
        )

    // Выводим Награду за босса
    @GetMapping("questBoss")
    fun playerRewardService() =
        playerRewardService.getGoldQuestBoss()

    // Принимаем GET коллекцию имен и выводим в json
    @GetMapping("player")
    fun getNamePlayers(@RequestParam names: List<String>): List<PlayerResponse> =
            playerRewardServiceNames.getPlayers(names)


    // Принимаем POST
    @PostMapping("search")
    fun addTask(@RequestBody helloResponse: HelloResponse) = "dsds"


}

data class HelloResponse(
    val name: String,
    val reward: String,
    val amount: Int
)