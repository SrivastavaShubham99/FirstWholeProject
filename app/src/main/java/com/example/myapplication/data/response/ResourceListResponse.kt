package com.example.myapplication.data.response

data class ResourceListResponse(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
){
    data class Data(
        val color: String?,
        val id: Int?,
        val name: String?,
        val pantone_value: String?,
        val year: Int?
    )

    data class Support(
        val text: String,
        val url: String
    )
}
