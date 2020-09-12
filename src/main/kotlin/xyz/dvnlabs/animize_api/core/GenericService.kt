/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api.core

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GenericService<E, ID> {
    fun findAll(pageable: Pageable): Page<E>

    fun findAll(): List<E>

    fun save(entity: E): E

    fun saveAll(entities: List<E>): List<E>

    fun delete(id: ID)

    fun update(entity: E, id: ID): E

    fun findById(id: ID): E
}