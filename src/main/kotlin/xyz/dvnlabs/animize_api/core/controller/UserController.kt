/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.controller

import com.mongodb.DuplicateKeyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.dvnlabs.animize_api.core.model.User
import xyz.dvnlabs.animize_api.core.model.UserLogin
import xyz.dvnlabs.animize_api.core.repository.UserRepository

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepo: UserRepository

    @PostMapping("/login")
    fun userLogin(@RequestBody user: UserLogin): RequestResponse {
        val data = userRepo.getUserByEmailAndPassword(user.email, user.password)
        return RequestResponse(
                error = true,
                message = "TEST",
                data = data
        )
    }

    @PostMapping("/")
    fun userCreate(@RequestBody user: User): RequestResponse {
        var error = true
        var message = "Nothing Happened!"
        var data: Any? = null
        try {
            error = false
            message = "Success!"
            data = userRepo.insert(user)
        } catch (e: DuplicateKeyException) {
            message = e.message!!
        }
        return RequestResponse(
                error = error,
                message = message,
                data = data
        )
    }
}