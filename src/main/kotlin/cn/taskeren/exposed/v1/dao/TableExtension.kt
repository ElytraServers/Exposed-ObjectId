package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.Table

/**
 * Creates an [ObjectId] column, with the specified [name].
 *
 * **Note:** This function is affected by the dialect of the database, where a length is passed by default,
 * which will cause a warning when working with PostgreSQL.
 * See [Table.binary] for more details.
 */
@Deprecated(
    "Use separated version after Kotlin 2.4",
    replaceWith = ReplaceWith("objectId(name).autoGenerate()"),
)
public fun Table.objectId(
    name: String,
    autoGenerate: Boolean = false,
): Column<ObjectId> = objectId(name).apply { if (autoGenerate) autoGenerate() }

/**
 * Creates an [ObjectId] column, with the specified [name].
 *
 * **Note:** This function is affected by the dialect of the database, where a length is passed by default,
 * which will cause a warning when working with PostgreSQL.
 * See [Table.binary] for more details.
 */
public fun Table.objectId(name: String): Column<ObjectId> = registerColumn(name, ObjectIdColumnType)

/**
 * [ObjectId] column will auto generate its value on the client side just before an insert.
 */
context(table: Table)
public fun Column<ObjectId>.autoGenerate(): Column<ObjectId> = with(table) { clientDefault { ObjectId() } }
