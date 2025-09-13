package com.yooshyasha.reactiveuserservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveUserServiceApplication

fun main(args: Array<String>) {
	runApplication<ReactiveUserServiceApplication>(*args)
}
