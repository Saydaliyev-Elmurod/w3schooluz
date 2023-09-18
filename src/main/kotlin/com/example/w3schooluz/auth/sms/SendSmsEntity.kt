package com.example.w3schooluz.auth.sms

import com.example.w3schooluz.profile.ProfileEntity
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime


@Getter
@Setter
@Entity
@Table(name = "session")
class SendSmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(name = "profile_id")
    private var profileId: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private val profile: ProfileEntity? = null

    @Column
    private var sms: String? = null

    @Column
    private var sendDate: LocalDateTime? = null

    constructor(profileId: Int?, sms: String?, sendDate: LocalDateTime?) {
        this.profileId = profileId
        this.sms = sms
        this.sendDate = sendDate
    }

    constructor()
}