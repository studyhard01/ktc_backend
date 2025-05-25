package com.example.demo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository


public class ScheduleRepo {
    private static JdbcTemplate jdbcTemplate = null;
    private final RowMapper<Schedule> scheduleRowMapper = new ScheduleRowMapper();

    public ScheduleRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public long insertSchedule(String title, String writer, LocalDateTime now) {
        String sql = "INSERT INTO schedule (title, content, writer, schedule_date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, title, "", writer, now.toLocalDate(), now, now);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    public void insertPassword(long scheduleId, String password) {
        String sql = "INSERT INTO schedule_auth (schedule_id, hashed_password, created_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, scheduleId, password, LocalDateTime.now());
    }

    public List<Schedule> findSchedules(String writer, String updatedDate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (writer != null && !writer.isEmpty()) {
            sql.append(" AND writer = ?");
            params.add(writer);
        }

        if (updatedDate != null && !updatedDate.isEmpty()) {
            sql.append(" AND DATE(updated_at) = ?");
            params.add(LocalDate.parse(updatedDate));
        }

        sql.append(" ORDER BY updated_at DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ScheduleRowMapper());
    }

    public static Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> results = jdbcTemplate.query(sql, new Object[]{id}, new ScheduleRowMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    public static String getPasswordByScheduleId(Long scheduleId) {
        String sql = "SELECT hashed_password FROM schedule_auth WHERE schedule_id = ?";
        List<String> result = jdbcTemplate.queryForList(sql, String.class, scheduleId);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void updateSchedule(Long id, String title, String writer, LocalDateTime updatedAt) {
        String sql = "UPDATE schedule SET title = ?, writer = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, writer, updatedAt, id);
    }

    public static void deleteScheduleById(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
