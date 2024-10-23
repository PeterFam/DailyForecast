package com.peterfam.dailyforecast.data.remote.response

import com.google.gson.annotations.SerializedName

data class CitiesResponse(

	@field:SerializedName("cities")
	val cities: List<CitiesItem>? = null
)

data class CitiesItem(

	@field:SerializedName("cityNameAr")
	val cityNameAr: String? = null,

	@field:SerializedName("cityNameEn")
	val cityNameEn: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)
