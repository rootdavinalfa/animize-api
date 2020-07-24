/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import xyz.dvnlabs.animize_api.core.model.Anime

@Component
interface AnimeRepository : MongoRepository<Anime, String> {
    fun getAnimeByPackageAnim(pkgID: String): Anime?
    fun getAnimeByGenre(genre: String): List<Anime>?
}