package com.sparta.schedule.dto.responsedto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String work;
    private String name;
    private String password;
    private String date1;
    private String date2;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.work = schedule.getWork();
        this.name = schedule.getName();
        this.password = schedule.getPassword();
        this.date1 = schedule.getDate1();
        this.date2 = schedule.getDate2();
    }

}

