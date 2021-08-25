package com.example.players

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@SpringBootApplication
class PlayersApplication

fun main(args: Array<String>){
	runApplication<PlayersApplication>(*args)
}

