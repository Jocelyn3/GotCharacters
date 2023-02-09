package com.example.characters.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.characters.utils.Util

@Entity(tableName = Util.CHARACTER_TABLE)
data class GotCharacter (
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "family") val family: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
) {
    override fun toString(): String {
        return "$firstName; " +
                "$lastName; " +
                "$fullName; " +
                "$title; " +
                "$family; " +
                "$imageUrl; "
    }
}

/*
    id": 0,
    "firstName": "Daenerys",
    "lastName": "Targaryen",
    "fullName": "Daenerys Targaryen",
    "title": "Mother of Dragons",
    "family": "House Targaryen",
    "image": "daenerys.jpg",
    "imageUrl": "https://thronesapi.com/assets/images/daenerys.jpg"
* */