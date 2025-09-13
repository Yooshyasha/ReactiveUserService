package com.yooshyasha.reactiveuserservice.dto

import com.yooshyasha.reactiveuserservice.entity.User

data class UserDTO(
    val id: Long,
    val name: String,
) {
    constructor(user: User) : this(user.id!!, user.name)
}
