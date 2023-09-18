package com.example.w3schooluz.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class MigrationConfiguration {
    @Value("\${spring.datasource.url}")
    private val dataSourceUrl: String? = null

    @Value("\${spring.datasource.username}")
    private val dataSourceUsername: String? = null

    @Value("\${spring.datasource.password}")
    private val dataSourcePassword: String? = null

    @get:Bean
    val dataSource: DataSource
        get() {
            val dataSourceBuilder = DataSourceBuilder.create()
            dataSourceBuilder.url(dataSourceUrl)
            dataSourceBuilder.username(dataSourceUsername)
            dataSourceBuilder.password(dataSourcePassword)
            return dataSourceBuilder.build()
        }
}