package com.example.players.controller

import com.example.players.service.PlayerRewardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PlayerController (
    private var playerRewardService: PlayerRewardService
        ) {
    @RequestMapping(value = ["/helloWorld"], method = [(RequestMethod.GET)])
    fun getHelloWordMessage(): ResponseEntity<String> =
        ResponseEntity.ok(
            playerRewardService.getHello()
        )

    @GetMapping("/service")
    fun playerRewardService() = playerRewardService.getHello()

    // Принимаем коллекцию имен и выводим в json
    @RequestMapping(value = ["/player/name={name}"], method = [(RequestMethod.GET)])
    fun getHelloWordMessageWithName(
        @PathVariable("name") name: String
    ): ResponseEntity<Any> =
        if (name != "Cristian") {
            ResponseEntity.ok(
                HelloResponse(
                    name = name,
                    reward = "Gold",
                    amount = 123
                )
            )
        } else {
            ResponseEntity.badRequest().body("I am Cristian")
        }
}

data class HelloResponse(
    val name: String,
    val reward: String,
    val amount: Int
)