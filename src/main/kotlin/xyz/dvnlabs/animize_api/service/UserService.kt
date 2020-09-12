/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.dvnlabs.animize_api.core.CommonHelper
import xyz.dvnlabs.animize_api.core.EncryptionHelper
import xyz.dvnlabs.animize_api.core.GenericService
import xyz.dvnlabs.animize_api.core.responses.BadAuthResponse
import xyz.dvnlabs.animize_api.core.responses.ExistsResponse
import xyz.dvnlabs.animize_api.core.responses.NotFoundResponse
import xyz.dvnlabs.animize_api.model.User
import xyz.dvnlabs.animize_api.repository.UserRepository
import javax.naming.AuthenticationException

@Service
@Transactional(rollbackFor = [Exception::class])
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var commonHelper: CommonHelper

    @Autowired
    private lateinit var userRepository: UserRepository


    override fun findAll(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun save(entity: User): User {
        if (userRepository.existsByEmail(entity.email)) {
            throw ExistsResponse("Exist!")
        }
        val max = commonHelper.queryMaxId("id", User::class.java)
        entity.id = if (max != null) {
            CommonHelper.fillString("USR", (max as User).id, 4)
        } else {
            "USR0001"
        }
        entity.password = EncryptionHelper.encode(entity.password)
        entity.registeredOn = System.currentTimeMillis()
        return userRepository.save(entity)
    }

    override fun saveAll(entities: List<User>): List<User> {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        return userRepository.findById(id)
                .map { return@map userRepository.deleteById(id) }
                .orElseThrow {
                    NotFoundResponse(id, "Not found")
                }
    }

    override fun update(entity: User, id: String): User {
        return userRepository.findById(id).map {
            return@map userRepository.save(entity)
        }.orElseThrow {
            NotFoundResponse("", "")
        }
    }

    override fun findById(id: String): User {
        return userRepository.findById(id).orElseThrow {
            NotFoundResponse(id, "Not found")
        }
    }

    override fun signIn(email: String, password: String): HashMap<Any, Any>? {
        try {
            /*authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))*/
            val user = userRepository.getUserByEmail(email) ?: return null
            //val token = jwtTokenProvider.createToken(userid = user.id, email = user.email, roles = user.roles)
            val model = HashMap<Any, Any>()
            model["email"] = email
            model["mode"] = "Bearer"
            model["token"] = ""
            return model
        } catch (e: AuthenticationException) {
            throw BadAuthResponse(email, password, e.explanation)
        }
    }
}

interface UserService : GenericService<User, String> {
    fun signIn(email: String, password: String): HashMap<Any, Any>?
}