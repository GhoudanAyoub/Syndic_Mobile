package com.syndicg5.networking.models

import androidx.room.util.TableInfo.Column
import java.util.Date


data class Reservation(
    val user: User? = null,
    val pitch: Pitches? = null,
    val reservationDate: Date? = null,
    val matchDate: Date? = null,
    val reservationPrice : Double = 0.0
)
