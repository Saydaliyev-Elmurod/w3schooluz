package com.example.w3schooluz.article

data class ArticleResponseDto(
    val id: Int?=null,
    val title: String? = null,
    val content:String?=null,
    val mainImage:String?=null,
    val images:List<String>?=null,
    val visible:Boolean?=false,
)

data class ArticleRequestDto(
    val title: String? = null,
    val content:String?=null,
    val mainImage:String?=null,
    val images:List<String>?=null,
)