package com.example.players.controller

import com.example.players.service.PlayerRewardService
import com.example.players.service.PlayerRewardServiceNames
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PlayerController (
    private var playerRewardService: PlayerRewardService,
    private var playerRewardServiceNames: PlayerRewardServiceNames
        ) {
    // Выводим Hello Services
    @RequestMapping(value = ["/helloWorld"], method = [(RequestMethod.GET)])
    fun getHelloWordMessage(): ResponseEntity<String> =
        ResponseEntity.ok(
            playerRewardService.getHello()
        )

    // Выводим Награду за босса
    @GetMapping("/questBoss")
    fun playerRewardService() = playerRewardService.getGoldQuestBoss()

    // Принимаем GET коллекцию имен и выводим в json
    @RequestMapping(value = ["/player/name={name}"], method = [(RequestMethod.GET)])
    fun getNamePlayers(
        @PathVariable("name") name: String
    ): ResponseEntity<Any> =
        if (name != "") {
            ResponseEntity.ok(
                playerRewardServiceNames.getPlayer(name)
            )
        } else {
            ResponseEntity.badRequest().body("No name")
        }

    // Принимаем POST
    @PostMapping("/search")
    fun addTask(@RequestBody helloResponse: HelloResponse) = "dsds"


}

data class HelloResponse(
    val name: String,
    val reward: String,
    val amount: Int
)