package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsKudaGoResponseDTO(
    var results: List<NewsDTO>
)

@Serializable
data class NewsDTO(
    var id: Long,
    @SerialName("publication_date")
    var publicationDate: Long,
    var title: String,
    var place: PlaceDTO?,
    var description: String,
    @SerialName("site_url")
    var siteUrl: String,
    @SerialName("favorites_count")
    var favoritesCount: Int,
    @SerialName("comments_count")
    var commentsCount: Int,
)

@Serializable
data class PlaceDTO(
    var id: Long
)