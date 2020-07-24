/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core.controller

import com.mongodb.DuplicateKeyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import xyz.dvnlabs.animize_api.core.model.Anime
import xyz.dvnlabs.animize_api.core.model.AnimeEpisode
import xyz.dvnlabs.animize_api.core.repository.AnimeRepository

data class RequestResponse(
        val error: Boolean,
        val message: String?,
        val data: Any?
)

@RestController
@RequestMapping("/anim")
class AnimeController {

    @Autowired
    private lateinit var animRepo: AnimeRepository

    @GetMapping("/data/{pkg}")
    fun animeData(@PathVariable pkg: String): RequestResponse {
        val data = animRepo.getAnimeByPackageAnim(pkg)
        return RequestResponse(
                error = true,
                message = "TEST",
                data = data
        )
    }

    @GetMapping("/genre/{genre}")
    fun animeGenre(@PathVariable genre: String): RequestResponse {
        val data = animRepo.getAnimeByGenre(genre)
        return RequestResponse(
                error = true,
                message = "TEST",
                data = data
        )
    }

    @GetMapping("/data/list")
    fun animeList(): RequestResponse {
        //Get all data from database with sort by packageAnim eg: PKG8,PKG7,...
        val data = animRepo.findAll(Sort.by(Sort.Direction.DESC, "packageAnim"))
        return RequestResponse(
                error = true,
                message = "TEST",
                data = data
        )
    }

    @PostMapping("/data")
    fun createNewAnime(@RequestBody anime: Anime): RequestResponse {
        var error = true
        var message = "Nothing Happened!"
        var data: Any? = null
        try {
            error = false
            message = "Success!"
            data = animRepo.insert(anime)
        } catch (e: DuplicateKeyException) {
            message = e.message!!
        }
        return RequestResponse(
                error = error,
                message = message,
                data = data
        )
    }

    @PostMapping("/data/episode")
    fun uploadNewEpisode(@RequestBody episode: AnimeEpisode): RequestResponse {
        var error = true
        var message = "Nothing Happened!"
        var data: Any? = null
        try {
            val x = episode.packageAnim?.let { animRepo.getAnimeByPackageAnim(it) }
            if (x?.episodes == null) {
                x?.episodes = arrayListOf()
            }
            x?.totalEpisode = x?.currentEpisode?.plus(episode.episode)!!
            x.currentEpisode = episode.episode
            x.episodes?.add(episode)
            data = animRepo.save(x as Anime)
            error = false
            message = "Success!!"
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