package com.pratical.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoModel(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()
) : Serializable {

    data class Items(
        @SerializedName("repo") var repo: String? = null,
        @SerializedName("repo_link") var repoLink: String? = null,
        @SerializedName("desc") var desc: String? = null,
        @SerializedName("lang") var lang: String? = null,
        @SerializedName("stars") var stars: String? = null,
        @SerializedName("forks") var forks: String? = null,
        @SerializedName("added_stars") var addedStars: String? = null,
        @SerializedName("is_select") var isSelect: Boolean? = false,
        @SerializedName("avatars") var avatars: ArrayList<String> = arrayListOf()

    ) : Serializable
}
