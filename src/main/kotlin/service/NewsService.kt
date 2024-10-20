package org.example.service

import org.example.model.News

interface NewsService {

    fun saveNews(path: String, news: Collection<News>)
}