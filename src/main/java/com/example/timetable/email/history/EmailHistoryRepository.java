package com.example.timetable.email.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {
    @Query("from EmailHistoryEntity where ")
    int countByEmailAndCreatedDateAfter(String email, LocalDateTime minus);
}
