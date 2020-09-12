/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import xyz.dvnlabs.animize_api.model.User

@Component
class CommonHelper {
    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    companion object {
        fun fillString(firstStr : String,seq: String, zeroFill: Int): String {
            val max = (seq.substring(seq.length - zeroFill)).toLong()
            return firstStr + String.format("%0" + zeroFill + "d", max + 1)
        }
    }
    fun queryMaxId(field : String,clazz: Class<*>) : Any? {
        val query = Query().limit(1).with(Sort.by(Sort.Direction.DESC, field))
        return mongoTemplate.findOne(query, clazz)
    }
}