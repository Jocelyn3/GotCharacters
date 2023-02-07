package com.example.characters.data.model

data class GotCharacter (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val imageUrl: String
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