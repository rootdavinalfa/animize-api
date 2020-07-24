/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import xyz.dvnlabs.animize_api.core.model.User

@Component
interface UserRepository : MongoRepository<User, String> {
    fun getUserByEmailAndPassword(email: String, password: String): User?
}