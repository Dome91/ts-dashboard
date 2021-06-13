package com.github.dome91.ts3dashboard.application.repositories

import com.github.dome91.ts3dashboard.core.factories.ID
import com.github.dome91.ts3dashboard.core.model.Entity
import com.github.dome91.ts3dashboard.core.repositories.Repository

abstract class InMemoryRepository<T : Entity> : Repository<T> {

    val store = HashMap<ID, T>()

    override fun save(entity: T) {
        store[entity.id] = entity
    }

    override fun findByID(id: ID): T? {
        return store[id]
    }

    override fun findAll(): List<T> {
        return store.values.toList()
    }

    override fun delete(entity: T) {
        store.remove(entity.id)
    }

    override fun deleteAll() {
        store.clear()
    }


}
