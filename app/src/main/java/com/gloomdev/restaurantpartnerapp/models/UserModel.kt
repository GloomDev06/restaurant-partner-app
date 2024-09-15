package com.gloomdev.restaurantpartnerapp.models

data class UserModel(
    val username: String? = null,
    val nameOfRestaurant: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val location: String? = null,
    val description: String? = null,
    val restaurantImage: String? = null
) {
    constructor(
        username: String?,
        nameOfRestaurant: String?,
        email: String?,
        phone: String?,
        location: String?
    ) : this(username, nameOfRestaurant, email, phone, location, "", "")
}
