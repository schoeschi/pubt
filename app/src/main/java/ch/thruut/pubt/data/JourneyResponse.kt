package ch.thruut.pubt.data

import kotlinx.serialization.Serializable

@Serializable
data class JourneyResponse(
    val connections: List<Connection>
)

@Serializable
data class Connection(
    val from: Checkpoint,
    val to: Checkpoint
)

@Serializable
data class Checkpoint(
    val arrival: String?,
    val arrivalTimestamp: Long?,
    val departure: String?,
    val departureTimestamp: Long?,
    val platform: String?,
    val prognosis: Prognosis,
    val station: Station
)

@Serializable
data class Prognosis(
    val platform: String?,
    val arrival: String?,
    val departure: String?,
    val capacity1st: String?,
    val capacity2nd: String?
)

@Serializable
data class Coordinate(
    val type: String,
    val x: Double,
    val y: Double
)