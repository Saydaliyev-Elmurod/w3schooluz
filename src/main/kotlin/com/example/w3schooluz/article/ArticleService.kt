package com.example.w3schooluz.article

import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun addArticle(dto: ArticleRequestDto): ArticleResponseDto {
        val entity = articleRepository.save(
            ArticleEntity(
                title = dto.title,
                content = dto.content,
                mainImage = dto.mainImage,
                images = dto.images,
            )
        )
        return toResponse(entity)
    }

        private fun toResponse(entity: ArticleEntity): ArticleResponseDto {
        return ArticleResponseDto(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            mainImage = entity.mainImage,
            images = entity.images,
            visible = entity.visible
        )
    }

    fun getById(id: Int): ArticleResponseDto {
        return toResponse(articleRepository.getReferenceById(id))
    }

    fun getAll(id: Int): MutableList<ArticleResponseDto> {
        val optional = articleRepository.findAll()
        val list = mutableListOf<ArticleResponseDto>()
        if (optional.isNotEmpty()) {
            optional.stream().forEach { o ->
                list.add(toResponse(o))
            }
        }
        return list
    }

    fun delete(id: Int): Boolean {
        return articleRepository.updateDeleted(id)==1
    }
}