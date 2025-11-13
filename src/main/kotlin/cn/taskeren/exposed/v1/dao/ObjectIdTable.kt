package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable

/**
 * Identity table with a primary key consisting of an auto-generating [ObjectId] value.
 *
 * **Note** The specific ObjectId column type used depends on the database.
 * The stored identity value will be auto-generated on the client side just before insertion of a new row.
 *
 * @param name Table name. By default, this will be resolved from any class name with a "Table" suffix removed (if present).
 * @param columnName Name for the primary key column. By default, "id" is used.
 */
public open class ObjectIdTable(
    name: String = "",
    columnName: String = "id",
) : IdTable<ObjectId>(name) {
    /** The identity column of this [ObjectIdTable], for storing ObjectId wrapped as [EntityID] instances. */
    final override val id: Column<EntityID<ObjectId>> = objectId(columnName, autoGenerate = true).entityId()
    final override val primaryKey: PrimaryKey = PrimaryKey(id)
}
