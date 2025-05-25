package com.example.demo;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ScheduleRequestDTO {
    private String title;
    private String writer;
    private String password;
}