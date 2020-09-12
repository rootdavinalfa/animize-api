/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.repository

import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import xyz.dvnlabs.animize_api.core.GenericRepository
import xyz.dvnlabs.animize_api.model.User
import java.util.*

@Component
@Repository
interface UserRepository : GenericRepository<User, String> {
    fun existsByEmail(email :String) : Boolean
    fun getUserByEmailAndPassword(email: String, password: String): User?
    fun getUserByEmail(email: String) : User?
}