package com.gift.buzzhub

data class Users(
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val userPhone: Long = 0,
    val userProvince: String = "", // Added default value
    val userCity: String = "" // Added default value
) {
    constructor() : this("", "", "", "", 0, "", "")
}