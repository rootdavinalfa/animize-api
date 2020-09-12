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

/*User Model*/
@Document
data class User(@Id
                var id: String = "",
                @Indexed(unique = true)
                @Field
                var email: String = "",
                @Field
                var name: String = "",
                @Field
                var userName: String? = null,
                @Field
                var password: String = "",
                @Field
                var registeredOn: Long = System.currentTimeMillis(),
                @Field
                var roles: List<String>?)