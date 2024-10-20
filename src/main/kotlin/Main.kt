package org.example

import org.example.client.NewsKudaGoClient
import org.example.mapper.NewsMapperImpl
import org.example.model.News
import org.example.service.NewsServiceImpl
import java.time.LocalDate

fun List<News>.getMostRatedNews(count: Int, period: ClosedRange<LocalDate>): List<News> {
    return this
        .filter { it.publicationDate in period }
        .sortedByDescending { it.rating }
        .take(count)
}

suspend fun main() {
    val mostRatedNews = NewsKudaGoClient(NewsMapperImpl())
        .getNews(500)
        .getMostRatedNews(
            5,
            LocalDate.of(2024, 8, 1)..LocalDate.now())
    NewsServiceImpl().saveNews("src/main/resources/news.csv", mostRatedNews)
}