package com.example.demo;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.example.demo.ScheduleRepo;

@Service

public class ScheduleService {

    private final ScheduleRepo repository;


    public ScheduleService(ScheduleRepo repository) {
        this.repository = repository;
    }

    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto) {
        LocalDateTime now = LocalDateTime.now();

        long scheduleId = repository.insertSchedule(dto.getTitle(), dto.getWriter(), now);
        repository.insertPassword(scheduleId, dto.getPassword());

        return new ScheduleResponseDTO(scheduleId, dto.getTitle(), dto.getWriter(), now, now);
    }

    public List<ScheduleResponseDTO> getSchedules(String writer, String updatedDate) {

        return repository.findSchedules(writer, updatedDate).stream()
                .map(schedule -> new ScheduleResponseDTO(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getWriter(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDTO getScheduleById(Long id) {
        Schedule schedule = ScheduleRepo.findById(id);
        if (schedule == null) {
            throw new NoSuchElementException("해당 일정이 존재하지 않습니다.");
        }

        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    public void updateSchedule(Long id, ScheduleUpdateRequestDTO dto) {
        String savedPassword = ScheduleRepo.getPasswordByScheduleId(id);
        if (savedPassword == null || !savedPassword.equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        ScheduleRepo.updateSchedule(id, dto.getTitle(), dto.getWriter(), LocalDateTime.now());
    }

    public void deleteSchedule(Long id, String inputPassword) {
        String savedPassword = ScheduleRepo.getPasswordByScheduleId(id);

        if (savedPassword == null || !savedPassword.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        ScheduleRepo.deleteScheduleById(id);
    }

}