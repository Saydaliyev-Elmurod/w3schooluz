package com.example.w3schooluz.article

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Category APIs ", description = "Admin category all API s")
@SecurityRequirement(name = "online shop ")
@RestController
@RequestMapping("article")
class ArticleController(val articleService: ArticleService) {
    @Operation(description = "add article ")
    @ApiResponse(
        responseCode = "200", content = [Content(schema = Schema(implementation = ArticleResponseDto::class))]
    )
    @PostMapping()
    fun post(@RequestBody articleRequestDto: ArticleRequestDto): ResponseEntity<*> {
        return ResponseEntity.ok(articleService.addArticle(articleRequestDto))
    }

    @Operation(description = "add article ")
    @ApiResponse(
        responseCode = "200", content = [Content(schema = Schema(implementation = ArticleResponseDto::class))]
    )
    @GetMapping()
    fun get(@PathVariable("id") id: Int): ResponseEntity<*> {
        return ResponseEntity.ok(articleService.getById(id))
    }

    @Operation(description = "add article ")
    @ApiResponse(
        responseCode = "200", content = [Content(schema = Schema(implementation = ArticleResponseDto::class))]
    )
    @GetMapping("/all")
    fun getAll(@PathVariable("id") id: Int): ResponseEntity<*> {
        return ResponseEntity.ok(articleService.getAll(id))
    }
    @Operation(description = "add article ")
    @ApiResponse(
        responseCode = "200", content = [Content(schema = Schema(implementation = ArticleResponseDto::class))]
    )
    @DeleteMapping()
    fun delete(@PathVariable("id") id: Int): ResponseEntity<*> {
        return ResponseEntity.ok(articleService.delete(id))
    }


}