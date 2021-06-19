package com.github.dome91.tsdashboard.core.repositories

import com.github.dome91.tsdashboard.core.factories.ID
import com.github.dome91.tsdashboard.core.model.Entity

interface Repository<T : Entity> {
    fun save(entity: T)
    fun findByID(id: ID): T?
    fun findAll(): List<T>
    fun delete(entity: T)
    fun deleteAll()
}
