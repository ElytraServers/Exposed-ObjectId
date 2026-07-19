@file:Suppress("ExposedReference")

package cn.taskeren.exposed.v1.dao

import org.bson.types.ObjectId
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.Transaction
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.test.Test

internal class TestObjectId {
    object PeopleTable : ObjectIdTable() {
        val name = varchar("name", 255)
    }

    class PeopleEntity(
        id: EntityID<ObjectId>,
    ) : ObjectIdEntity(id) {
        companion object : ObjectIdEntityClass<PeopleEntity>(PeopleTable)

        var name by PeopleTable.name

        context(_: Transaction)
        fun entityToString(): String = "PeopleEntity(id='${id.value}', name='$name')"
    }

    @Test
    fun testAll() {
        @Suppress("UnusedVariable", "unused")
        val db = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(PeopleTable)
        }

        transaction {
            PeopleEntity.new {
                name = "Jackson"
            }
        }

        transaction {
            val all = PeopleEntity.all().toList()
            check(all.size == 1)

            val jackson = all.single()
            check(jackson.name == "Jackson")
            println(jackson.entityToString())
        }
    }
}
