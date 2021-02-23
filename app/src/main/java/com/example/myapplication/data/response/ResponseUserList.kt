package com.example.myapplication.data.response


import com.google.gson.annotations.SerializedName

data class ResponseUserList(
    val `data`: List<Data?>?,
    val page: Int?, // 2
    @SerializedName("per_page")
    val perPage: Int?, // 6
    val support: Support?,
    val total: Int?, // 12
    @SerializedName("total_pages")
    val totalPages: Int? // 2
) {
    data class Data(
        val avatar: String?, // https://reqres.in/img/faces/7-image.jpg
        val email: String?, // michael.lawson@reqres.in
        @SerializedName("first_name")
        val firstName: String?, // Michael
        val id: Int?, // 7
        @SerializedName("last_name")
        val lastName: String? // Lawson
    )

    data class Support(
        val text: String?, // To keep ReqRes free, contributions towards server costs are appreciated!
        val url: String? // https://reqres.in/#support-heading
    )
}