package com.example.w3schooluz.profile

import jakarta.persistence.*
@Entity
@Table(name = "profile")
class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "surname")
    var surname: String? = null,

    @Column(name = "email")
    val email: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: ProfileRole? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: ProfileStatus? = null
)