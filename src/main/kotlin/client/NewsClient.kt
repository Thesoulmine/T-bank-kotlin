package org.example.client

import org.example.model.News

interface NewsClient {

    suspend fun getNews(count: Int = 100): List<News>
}