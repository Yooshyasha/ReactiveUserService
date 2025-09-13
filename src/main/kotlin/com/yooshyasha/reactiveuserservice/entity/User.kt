package com.yooshyasha.reactiveuserservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    val id: Long?,

    val name: String,
)
