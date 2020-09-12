/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.responses

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * [lookup] Argument used for searching
 * [message] Detailed message
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundResponse(
        private val lookup: String,
        message: String
) : RuntimeException(message)