package com.example.w3schooluz.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ArticleRepository : JpaRepository<ArticleEntity, Int> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity set deleted = true where id = ?1")
    fun updateDeleted(id: Int): Int
}