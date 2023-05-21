package com.example.genshincharactercompose.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "genshin_hero")
class HeroEntity (
    @ColumnInfo(name = "name")
    @field:PrimaryKey
    val name: String,

    @field:ColumnInfo(name = "photoUrl")
    val photoUrl: String,

    @field:ColumnInfo(name = "rating")
    val rating: String,

    @field:ColumnInfo(name= "vision")
    val vision: String
): Parcelable