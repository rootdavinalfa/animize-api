/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import xyz.dvnlabs.animize_api.core.responses.SuccessResponse
import xyz.dvnlabs.animize_api.model.User
import xyz.dvnlabs.animize_api.model.UserLogin
import xyz.dvnlabs.animize_api.service.UserService

@RestController
@RequestMapping("/v3/user")
@Api(tags = ["User Control Mapping"])
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/login")
    @ApiOperation("Login user")
    fun userLogin(@RequestBody user: UserLogin) {
        userService.signIn(user.email, user.password)
    }

    @PostMapping("/register")
    @ApiOperation("Create user")
    fun userCreate(@RequestBody user: User): SuccessResponse {
        val data = userService.save(user)
        return SuccessResponse(
                message = "Success created user!",
                data = data
        )
    }

    @PutMapping("/")
    @ApiOperation("Update user")
    fun userUpdate(@RequestBody user: User): SuccessResponse {
        val data = userService.update(user, user.id)
        return SuccessResponse(
                message = "Success updated user!",
                data = data
        )
    }

    @DeleteMapping("/")
    @ApiOperation("Delete user")
    fun userDelete(@RequestHeader userID: String): SuccessResponse {
        val data = userService.delete(userID)
        return SuccessResponse(
                message = "Success deleted user!",
                data = data
        )
    }
}