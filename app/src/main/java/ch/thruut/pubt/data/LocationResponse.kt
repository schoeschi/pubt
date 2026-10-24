package ch.thruut.pubt.data

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(val stations: List<Station>)

@Serializable
data class Station(
    val coordinate: Coordinate,
    val id: String,
    val name: String,
    val score: Int?
)
