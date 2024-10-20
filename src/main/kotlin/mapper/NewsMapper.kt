package org.example.mapper

import org.example.dto.NewsDTO
import org.example.model.News

interface NewsMapper {

    fun fromDTO(newsDTO: NewsDTO): News

    fun fromDTO(newsDTOList: List<NewsDTO>): List<News>
}