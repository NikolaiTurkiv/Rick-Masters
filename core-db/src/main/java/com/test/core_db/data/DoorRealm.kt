package com.test.core_db.data

import io.realm.RealmObject

open class DoorRealm(
    var name: String?,
    var room: String?,
    var id: Int?,
    var favorites: Boolean?,
    var snapshot: String?,
): RealmObject()