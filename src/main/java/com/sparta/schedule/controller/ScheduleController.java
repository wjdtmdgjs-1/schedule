package com.sparta.schedule.controller;

import com.sparta.schedule.dto.requestdto.DeleteRequestDto;
import com.sparta.schedule.dto.requestdto.FixRequestDto;
import com.sparta.schedule.dto.requestdto.GetRequestDto;
import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.responsedto.FixResponseDto;
import com.sparta.schedule.dto.responsedto.GetResponseDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<Long, Schedule> scheduleList = new HashMap<>();


    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);


    }
    //선택한 일정 조회
    @GetMapping("/schedule/{id}")
    public GetResponseDto getOneSchedule(@PathVariable Long id, @RequestBody GetRequestDto requestDto) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getOneSchedule(id, requestDto);
    }


    //일정 목록 조회
    // GET http://localhost:8080/api/schedule?name=정승헌
    @GetMapping("/schedule")
    public List<GetResponseDto> getSchedules(@RequestParam(required = false) String date2, @RequestParam(required = false) String name){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSchedules(date2, name);
    }
    //http://localhost:8080/api/schedules/fix
    @PutMapping("/schedule/fix")
    public FixResponseDto fixSchedule(@RequestBody FixRequestDto requestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.fixSchedule(requestDto);
    }

    @DeleteMapping("/schedule/delete")
    public void deleteSchedule(@RequestBody DeleteRequestDto requestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        scheduleService.deleteSchedule(requestDto);
    }


}

