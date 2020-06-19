package com.example.news.model.sources

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("sources")
	val sources: List<SourcesItem>,

	@field:SerializedName("status")
	val status: String
)