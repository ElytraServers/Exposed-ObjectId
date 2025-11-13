package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.Table

/**
 * Whether call [Table.binary] with `length` or not when creating an ObjectId column.
 *
 * It's `true` by default to work with majority of database dialects.
 * Only PostgreSQL will complain with this with a warning message.
 *
 * See [Table.binary] for more details.
 */
private var binaryColumnWithLength: Boolean = true

/**
 * Creates an [ObjectId] column, with the specified [name].
 *
 * **Note:** This function is affected by the dialect of the database, where a length is passed by default,
 * which will cause a warning when working with PostgreSQL.
 * See [Table.binary] for more details.
 */
public fun Table.objectId(
    name: String,
    autoGenerate: Boolean = false,
): Column<ObjectId> =
    byteArray(name, length = 12)
        .transform(wrap = ::byteArrayToObjectId, unwrap = ::objectIdToByteArray)
        .apply { if (autoGenerate) clientDefault { ObjectId() } }

private fun Table.byteArray(
    name: String,
    length: Int,
): Column<ByteArray> = if (binaryColumnWithLength) this.binary(name, length) else this.binary(name)

private fun byteArrayToObjectId(byteArray: ByteArray) = ObjectId(byteArray)

private fun objectIdToByteArray(objectId: ObjectId) = objectId.toByteArray()
