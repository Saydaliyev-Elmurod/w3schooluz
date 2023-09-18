package com.example.w3schooluz.article

import jakarta.persistence.*

@Entity
class ArticleImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", updatable = false, insertable = false)
    val article: ArticleEntity? = null,
    @Column(name = "article_id")
    val articleId: Int? = null,
    val type: ImageType? = null
)