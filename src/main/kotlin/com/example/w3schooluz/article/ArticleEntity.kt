package com.example.w3schooluz.article

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
data class ArticleEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    val mainImage: String? = null,
    @ElementCollection(targetClass = String::class, fetch = FetchType.EAGER)
    @CollectionTable(
        name = "images",
        joinColumns = [JoinColumn(name = "article_id")]
    )
    @Column(name = "image", nullable = false)
    var images: List<String>? = null,
    val visible: Boolean? = null,
    val deleted: Boolean? = null,
    val createdDate: Timestamp = Timestamp(System.currentTimeMillis()),
    val updateDate: Timestamp? = null
)