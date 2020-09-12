/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import xyz.dvnlabs.animize_api.core.Constant

@Document
data class Anime(
        @Id
        @Indexed
        var id: String,
        @Indexed
        @Field
        var nameCatalogue: String,
        @Field
        var synopsis: String,
        @Field
        var totalEpisode: Int = 0,
        @Field
        var currentEpisode: Int = 0,
        @Field
        var malID: Int = 0,
        @Field
        var rating: Double = 0.0,
        var cover: String = "${Constant.CDN_PKG}${id}.jpg",
        @Field
        var genre: MutableList<String>? = null,
        @Field
        var episodes: MutableList<AnimeEpisode>? = null
)