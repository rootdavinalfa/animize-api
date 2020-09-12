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
import xyz.dvnlabs.animize_api.core.GenericService
import xyz.dvnlabs.animize_api.core.responses.ExistsResponse
import xyz.dvnlabs.animize_api.core.responses.NotFoundResponse
import xyz.dvnlabs.animize_api.model.Anime
import xyz.dvnlabs.animize_api.model.AnimeEpisode
import xyz.dvnlabs.animize_api.model.User
import xyz.dvnlabs.animize_api.repository.AnimeRepository

@Service
@Transactional(rollbackFor = [Exception::class])
class AnimeServiceImpl : AnimeService {
    @Autowired
    private lateinit var animeRepository: AnimeRepository

    @Autowired
    private lateinit var commonHelper: CommonHelper

    override fun findAll(pageable: Pageable): Page<Anime> {
        return animeRepository.findAll(pageable)
    }

    override fun findAll(): List<Anime> {
        return animeRepository.findAll()
    }

    override fun save(entity: Anime): Anime {
        if (animeRepository.existsById(entity.id)) {
            throw ExistsResponse("Anime with ID ${entity.id} is exist")
        }
        val max = commonHelper.queryMaxId("id", Anime::class.java)
        entity.id = if (max != null) {
            CommonHelper.fillString("PKG", (max as Anime).id, 4)
        } else {
            "PKG0001"
        }
        return animeRepository.save(entity)
    }

    override fun saveAll(entities: List<Anime>): List<Anime> {
        val ids = entities.map { it.id }.toList()
        if (animeRepository.existsByIdIn(ids)) {
            throw ExistsResponse("Anime with ID $ids is exist")
        }
        return animeRepository.saveAll(entities)
    }

    override fun delete(id: String) {
        return animeRepository.findById(id).map {
            return@map animeRepository.deleteById(id)
        }.orElseThrow {
            throw NotFoundResponse("Anime with ID: $id", "Not found")
        }
    }

    override fun update(entity: Anime, id: String): Anime {
        return animeRepository.findById(id).map {
            return@map animeRepository.save(entity)
        }.orElseThrow {
            throw NotFoundResponse("Anime with ID: $id", "Not found")
        }
    }

    override fun findById(id: String): Anime {
        return animeRepository.findById(id).orElseThrow {
            throw NotFoundResponse("Anime with ID: $id", "Not found")
        }
    }

    override fun findByGenre(genre: String): List<Anime> {
        return animeRepository.findAllByGenre(genre)
    }

    override fun newEpisode(episode: AnimeEpisode): Anime {
        if (!animeRepository.existsById(episode.packageAnim)) {
            throw NotFoundResponse("ID ${episode.packageAnim}", message = "Not found")
        }
        val anime = animeRepository.findById(episode.packageAnim)
        if (!anime.isPresent) {
            throw NotFoundResponse("ID ${episode.packageAnim}", message = "Not found")
        }
        val anims = anime.get()
        anims.id.toUpperCase()
        anims.totalEpisode = anims.currentEpisode + 1
        anims.currentEpisode = anims.currentEpisode + 1
        if (anims.episodes == null) {
            anims.episodes = ArrayList()
        }
        anims.episodes?.add(episode)
        return animeRepository.save(anims)
    }

    override fun findSearch(search: String): List<Anime> {
        return animeRepository.findAllByNameCatalogueLikeIgnoreCase(search)
    }
}

interface AnimeService : GenericService<Anime, String> {
    fun findByGenre(genre: String): List<Anime>
    fun newEpisode(episode: AnimeEpisode): Anime
    fun findSearch(search : String) : List<Anime>
}