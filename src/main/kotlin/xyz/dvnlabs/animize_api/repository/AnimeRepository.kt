/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.repository

import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import xyz.dvnlabs.animize_api.core.GenericRepository
import xyz.dvnlabs.animize_api.model.Anime
import java.util.*

@Component
@Repository
interface AnimeRepository : GenericRepository<Anime, String> {
    fun findAllByGenre(genre: String): List<Anime>
    fun existsByIdIn(pkgIDs: List<String>): Boolean
}