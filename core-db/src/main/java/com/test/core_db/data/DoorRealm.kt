package com.test.core_db.data

import io.realm.RealmObject

open class DoorRealm(): RealmObject(){
    var keyId: Int? = null
    var name: String? = null
    var room: String? = null
    var id: Int? = null
    var favorites: Boolean? = null
    var snapshot: String? = null
}