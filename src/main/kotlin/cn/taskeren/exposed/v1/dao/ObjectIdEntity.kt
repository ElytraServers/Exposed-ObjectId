package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.dao.Entity
import org.jetbrains.exposed.v1.dao.EntityClass

/** Base class for an [Entity] instance identified by an [id] comprised of a wrapped `ObjectId` value. */
public abstract class ObjectIdEntity(
    id: EntityID<ObjectId>,
) : Entity<ObjectId>(id)

/**
 * Base class representing the [EntityClass] that manages [ObjectIdEntity] instances and
 * maintains their relation to the provided [table].
 *
 * @param table The [IdTable] object that stores rows mapped to entities of this class.
 * @param entityType The expected [ObjectIdEntity] type.
 * This can be left `null` if it is the class of type argument [E] provided to this [ObjectIdEntityClass] instance.
 * If this `ObjectIdEntityClass` is defined as a companion object of a custom `ObjectIdEntity` class,
 * the parameter will be set to this immediately enclosing class by default.
 * @param entityCtor The function invoked to instantiate a [ObjectIdEntity] using a provided [EntityID] value.
 * If a reference to a specific constructor or a custom function is not passed as an argument,
 * reflection will be used to determine the primary constructor of the associated entity class on first access.
 * If this `ObjectIdEntityClass` is defined as a companion object of a custom `ObjectIdEntity` class,
 * the constructor will be set to that of the immediately enclosing class by default.
 */
public abstract class ObjectIdEntityClass<out E : ObjectIdEntity>(
    table: IdTable<ObjectId>,
    entityType: Class<E>? = null,
    entityCtor: ((EntityID<ObjectId>) -> E)? = null,
) : EntityClass<ObjectId, E>(table, entityType, entityCtor)
