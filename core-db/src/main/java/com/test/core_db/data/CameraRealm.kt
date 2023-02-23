package com.test.core_db.data

import io.realm.RealmObject

open class CameraRealm(
    var name: String?,
    var snapshot: String?,
    var room: String?,
    var id: Int?,
    var favorites: Boolean?,
    var rec: Boolean?,
): RealmObject()