package com.example.genshincharactercompose.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "description")
class DetailHeroEntity (
    @PrimaryKey
    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "weapon")
    val weapon: String,

    @field:ColumnInfo(name = "constellation")
    val constellation: String,

    @field:ColumnInfo(name= "region")
    val region: String,

    @field:ColumnInfo(name="description")
    val description :String
): Parcelable