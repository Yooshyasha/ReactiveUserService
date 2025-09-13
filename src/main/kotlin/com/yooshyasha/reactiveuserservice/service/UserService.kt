package com.yooshyasha.reactiveuserservice.service

import com.yooshyasha.reactiveuserservice.dto.UserDTO
import com.yooshyasha.reactiveuserservice.entity.User
import com.yooshyasha.reactiveuserservice.repo.UserRepository
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

    suspend fun getAllUsers(): Collection<UserDTO> {
        val users = userRepository.findAll()
            .map {
                UserDTO(it)
            }.collectList().awaitSingle()

        return users
    }
}