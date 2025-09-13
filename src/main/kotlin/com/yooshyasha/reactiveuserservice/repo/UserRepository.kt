package com.yooshyasha.reactiveuserservice.repo

import com.yooshyasha.reactiveuserservice.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : ReactiveCrudRepository<User, Long> {
}