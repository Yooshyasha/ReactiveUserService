package com.yooshyasha.reactiveuserservice.controller

import com.yooshyasha.reactiveuserservice.dto.UserDTO
import com.yooshyasha.reactiveuserservice.dto.controller.user.RequestCreateUser
import com.yooshyasha.reactiveuserservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/create")
    suspend fun createUser(@RequestBody createData: RequestCreateUser): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.createUser(createData.name))
    }

    @GetMapping("/all")
    suspend fun getAllUsers(): ResponseEntity<Collection<UserDTO>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }
}