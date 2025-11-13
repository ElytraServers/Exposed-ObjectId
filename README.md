# Exposed Object Id

![Maven Central](https://img.shields.io/maven-central/v/cn.elytra/exposed-object-id)
![GitHub License](https://img.shields.io/github/license/ElytraServers/Exposed-ObjectId)

An extension to [Exposed](https://github.com/JetBrains/Exposed) in supporting ObjectId as primary keys, for developers
who want to migrate their data from MongoDB to any other RDB.

## Usage

```kotlin
object MyTable : Table() {
	// `autoGenerate` parameter is like calling autoGenerate() to UUID columns, where
	// the value will be auto-generated on the client side just before insertion of a new row.
	val foo = objectId("foo", autoGenerate = false)
}
```

```kotlin
object MyTable : ObjectIdTable() {
	// ...
}

class MyEntity(id: EntityID<ObjectId>) : ObjectIdEntity(id) {
	companion object : ObjectIdEntityClass<MyEntity>(MyTable)
	// ...
}
```
