package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Schedule {
    private Long id;
    private String title;
    private String writer;
    private LocalDate scheduleDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule(Long id, String title, String writer, LocalDate scheduleDate,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.scheduleDate = scheduleDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getWriter() { return writer; }
    public LocalDate getScheduleDate() { return scheduleDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}

