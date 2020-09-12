/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Field

data class AnimeEpisode(
        @Indexed
        @Field
        val packageAnim: String,
        @Field
        val episode: Int = 1,
        @Field
        val source: String,
        @Field
        val uploaded: Long = System.currentTimeMillis()
)