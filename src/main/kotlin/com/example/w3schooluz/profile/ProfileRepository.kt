package com.example.w3schooluz.profile

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*


interface ProfileRepository : CrudRepository<ProfileEntity?, Int?> {
    @Query("from  ProfileEntity where email = ?1 ")
    fun findByEmail(email: String?): Optional<ProfileEntity>

    @Modifying
    @Transactional
    @Query("update ProfileEntity set password =?2 where id = ?1 ")
    fun updatePassword(id: Int?, pass: String?): Int

    @Modifying
    @Transactional
    @Query("update ProfileEntity set email =?2 where id = ?1 ")
    fun updateEmail(id: Int?, email: String?): Int

    @Modifying
    @Transactional
    @Query("update ProfileEntity set name =?2, surname = ?3 where id = ?1 ")
    fun updateNameAndSurname(id: Int?, name: String?, surname: String?): Int

    @Query(value = " select * from profile where id = ?1 ", nativeQuery = true)
    fun findByProfile(id: Int?): ProfileEntity
}