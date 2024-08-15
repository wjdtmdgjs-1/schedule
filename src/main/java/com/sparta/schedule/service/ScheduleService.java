package com.sparta.schedule.service;


import com.sparta.schedule.dto.requestdto.DeleteRequestDto;
import com.sparta.schedule.dto.requestdto.FixRequestDto;
import com.sparta.schedule.dto.requestdto.GetRequestDto;
import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.responsedto.FixResponseDto;
import com.sparta.schedule.dto.responsedto.GetResponseDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    public GetResponseDto getOneSchedule(Long id) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule != null) {
            return new GetResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public List<GetResponseDto> getSchedules(String date2, String name) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        List<GetResponseDto> getResponseDtos = scheduleRepository.find(date2, name);
        return getResponseDtos;
    }

    public FixResponseDto fixSchedule(FixRequestDto requestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.fix(requestDto);
        Schedule schedule = scheduleRepository.findById(requestDto.getId());
        return new FixResponseDto(schedule);
    }

    public void deleteSchedule(DeleteRequestDto requestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.delete(requestDto);
    }
}

