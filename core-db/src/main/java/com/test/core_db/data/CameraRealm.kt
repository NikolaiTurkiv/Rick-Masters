package com.test.core_db.data

import io.realm.RealmObject

open class CameraRealm: RealmObject(){
    var name: String? = null
    var snapshot: String? = null
    var room: String? = null
    var id: Int? = null
    var favorites: Boolean? = null
    var rec: Boolean? = null
}