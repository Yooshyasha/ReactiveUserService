package com.yooshyasha.reactiveuserservice.service

import com.yooshyasha.reactiveuserservice.dto.UserDTO
import com.yooshyasha.reactiveuserservice.entity.User
import com.yooshyasha.reactiveuserservice.repo.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    suspend fun createUser(name: String): UserDTO {
        val user = User(name = name).run {
            userRepository.save(this)
        }.awaitSingle()

        return UserDTO(user)
    }

    suspend fun getAllUsers(): Collection<UserDTO> = coroutineScope {
//        val users = userRepository.findAll()
//            .map {
//                UserDTO(it)
//            }.collectList().awaitSingle()

        // Код ниже исключительно для практики

        val channel = Channel<User>()

        launch {
            userRepository.findAll().asFlow().collect {
                channel.send(it)
            }

            channel.close()
        }

        val users = mutableListOf<UserDTO>()
        launch {
            channel.receiveAsFlow().collect {
                users.add(UserDTO(it))
            }
        }

        return@coroutineScope users
    }
}