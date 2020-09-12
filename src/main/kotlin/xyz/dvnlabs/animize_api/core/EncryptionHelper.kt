/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

class EncryptionHelper {
    companion object {
        fun encode(str: String): String {
            val bcrypt = BCryptPasswordEncoder(10, SecureRandom())
            return bcrypt.encode(str)
        }
    }
}