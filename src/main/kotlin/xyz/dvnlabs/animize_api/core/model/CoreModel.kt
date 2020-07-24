/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Anime(
        @Id
        val id: String? = null,
        @Indexed(unique = true)
        val packageAnim: String,
        var nameCatalogue: String, var synopsis: String,
        var totalEpisode: Int = 0, var currentEpisode: Int = 0, var malID: Int = 0,
        var rating: Double = 0.0,
        var genre: MutableList<String>? = null,
        var episodes: MutableList<AnimeEpisode>? = null
)

data class AnimeEpisode(
        val packageAnim: String?,
        val episode: Int = 1,
        val source: String,
        val uploaded: Long = System.currentTimeMillis()
)


/*User Model*/
@Document
data class User(
        @Id
        val id: String? = null,
        @Indexed(unique = true)
        var email: String,
        var userName: String? = null,
        var password: String,
        val registeredOn: Long = System.currentTimeMillis()
)

data class UserLogin(
        val email: String,
        val password: String
)
