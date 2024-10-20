package org.example.model

import java.time.LocalDate
import kotlin.math.exp

data class News(
    var id: Long,
    var publicationDate: LocalDate,
    var title: String,
    var place: Place?,
    var description: String,
    var siteUrl: String,
    var favoritesCount: Int,
    var commentsCount: Int,
) {
    val rating: Double by lazy {
        1 / (1 + exp(-(favoritesCount / (commentsCount + 1).toDouble())))
    }
}