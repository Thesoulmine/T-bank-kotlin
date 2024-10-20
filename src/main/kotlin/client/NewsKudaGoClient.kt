package org.example.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.dto.NewsKudaGoResponseDTO
import org.example.mapper.NewsMapper
import org.example.model.News

class NewsKudaGoClient constructor(
    private val newsMapper: NewsMapper
) : NewsClient {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getNews(count: Int): List<News> {
        val newsList = mutableListOf<News>()
        var page = 1

        while (newsList.size < count) {
            val response: NewsKudaGoResponseDTO = client.get("https://kudago.com/public-api/v1.4/news/") {
                url {
                    parameters.append("page", page.toString())
                    parameters.append("page_size", "100")
                    parameters.append("fields", "id,publication_date,title,place,description,site_url,favorites_count,comments_count")
                    parameters.append("location", "msk")
                }
            }.body()

            val responseNewsList = newsMapper.fromDTO(response.results)
            newsList.addAll(responseNewsList.take(count - newsList.size))

            if (responseNewsList.isEmpty()) {
                break
            }

            page++
        }

        return newsList
    }
}