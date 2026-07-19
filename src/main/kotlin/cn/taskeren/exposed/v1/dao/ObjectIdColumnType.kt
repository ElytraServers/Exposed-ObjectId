package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.BinaryColumnType
import org.jetbrains.exposed.v1.core.ColumnType
import org.jetbrains.exposed.v1.core.vendors.PostgreSQLDialect
import org.jetbrains.exposed.v1.core.vendors.currentDialect

private const val LENGTH = 12

internal object ObjectIdColumnType : ColumnType<ObjectId>() {
    private val delegate = BinaryColumnType(LENGTH)

    override fun sqlType(): String {
        val dialect = currentDialect
        if (dialect is PostgreSQLDialect) {
            // warns if with length
            return dialect.dataTypeProvider.binaryType()
        }
        // others are working well with length variant
        return dialect.dataTypeProvider.binaryType(LENGTH)
    }

    override fun validateValueBeforeUpdate(value: ObjectId?) {
        val value = value?.toByteArray()
        delegate.validateValueBeforeUpdate(value)
    }

    override fun valueFromDB(value: Any): ObjectId = ObjectId(delegate.valueFromDB(value))

    override fun notNullValueToDB(value: ObjectId): Any = value.toByteArray()

    override fun nonNullValueToString(value: ObjectId): String = delegate.valueToString(value.toByteArray())
}
