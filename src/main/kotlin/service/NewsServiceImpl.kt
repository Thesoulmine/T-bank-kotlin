package org.example.service

import org.apache.commons.csv.CSVFormat
import org.example.model.News
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

class NewsServiceImpl : NewsService {

    override fun saveNews(path: String, news: Collection<News>) {
        val filePath = Paths.get(path)

        if (!Files.isDirectory(filePath.parent)) {
            throw IllegalArgumentException("Неверный путь")
        }

        if (Files.exists(filePath)) {
            throw IllegalArgumentException("Файл уже существует")
        }

        FileWriter(path).use { writer ->
            CSVFormat.DEFAULT.print(writer).apply {
                printRecord("Id", "Pubication date", "Title", "Place", "Description", "Site url", "Favorites count", "Comments count", "Rating")
                news.forEach { news ->
                    printRecord(
                        news.id,
                        news.publicationDate,
                        news.title,
                        news.place,
                        news.description,
                        news.siteUrl,
                        news.favoritesCount,
                        news.commentsCount,
                        news.rating)
                }
            }
            writer.close()
        }
    }
}