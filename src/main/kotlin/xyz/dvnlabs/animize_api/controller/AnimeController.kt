/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import xyz.dvnlabs.animize_api.core.responses.SuccessResponse
import xyz.dvnlabs.animize_api.model.Anime
import xyz.dvnlabs.animize_api.model.AnimeEpisode
import xyz.dvnlabs.animize_api.service.AnimeService
import javax.validation.Valid

@RestController
@RequestMapping("/v3/anim")
@Api(tags = ["Anime Request Mapping"])
class AnimeController {

    @Autowired
    private lateinit var service: AnimeService

    @GetMapping("/{pkg}")
    @ApiOperation("Get anime by package")
    fun animeData(@PathVariable pkg: String): SuccessResponse {
        val data = service.findById(pkg)
        return SuccessResponse(
                message = "Anime by id : $pkg",
                data = data
        )
    }

    @GetMapping("/genre/{genre}")
    @ApiOperation("Get anime by genre")
    fun animeGenre(@PathVariable genre: String): SuccessResponse {
        val data = service.findByGenre(genre)
        return SuccessResponse(
                message = "Anime by genre $genre",
                data = data
        )
    }

    @GetMapping("/list")
    @ApiOperation("Get anime list")
    fun animeList(@ApiParam("Specified search query") @RequestParam("search", defaultValue = "") search: String): SuccessResponse {
        val data = if (search.isEmpty()) {
            service.findAll()
        } else {
            service.findSearch(search)
        }
        return SuccessResponse(
                message = "All Anime List",
                data = data
        )
    }

    @GetMapping("/page")
    @ApiOperation("Get anime page")
    fun animePage(pageable: Pageable): SuccessResponse {
        val data = service.findAll(pageable)
        return SuccessResponse(
                message = "All Anime pageable",
                data = data
        )
    }

    @PostMapping
    @ApiOperation("Create new anime")
    fun createNewAnime(@RequestBody anime: Anime): SuccessResponse {
        val data = service.save(anime)
        return SuccessResponse(
                message = "Success",
                data = data
        )
    }

    @PostMapping("/batch")
    @ApiOperation("Create batch new anime")
    fun createNewAnime(@RequestBody anime: List<Anime>): SuccessResponse {
        val data = service.saveAll(anime)
        return SuccessResponse(
                message = "Success",
                data = data
        )
    }

    @PutMapping
    @ApiOperation("Update anime data")
    fun updateAnime(@RequestBody anime: Anime): SuccessResponse {
        val data = service.update(anime, anime.id)
        return SuccessResponse(
                message = "Success Updating",
                data = data
        )
    }

    @PostMapping("/episode")
    @ApiOperation("Create new episode")
    fun uploadNewEpisode(@Valid @RequestBody episode: AnimeEpisode): SuccessResponse {
        val data = service.newEpisode(episode)
        return SuccessResponse(
                message = "Success added $episode",
                data = data
        )

    }

}