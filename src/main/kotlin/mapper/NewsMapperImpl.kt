package org.example.mapper

import org.example.dto.NewsDTO
import org.example.model.News
import org.example.model.Place
import java.time.Instant
import java.time.ZoneId

class NewsMapperImpl : NewsMapper {

    override fun fromDTO(newsDTO: NewsDTO): News {
        return News(
            newsDTO.id,
            Instant.ofEpochSecond(newsDTO.publicationDate).atZone(ZoneId.systemDefault()).toLocalDate(),
            newsDTO.title,
            newsDTO.place?.let { Place(it.id) },
            newsDTO.description,
            newsDTO.siteUrl,
            newsDTO.favoritesCount,
            newsDTO.commentsCount
        )
    }

    override fun fromDTO(newsDTOList: List<NewsDTO>): List<News> {
        return newsDTOList.map { newsDTO -> fromDTO(newsDTO) }
    }
}