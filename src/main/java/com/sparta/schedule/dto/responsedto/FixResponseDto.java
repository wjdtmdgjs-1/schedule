package com.sparta.schedule.dto.responsedto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class FixResponseDto {
    private Long id;
    private String work;
    private String name;
    private String password;
    private String date2;

    public FixResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.work = schedule.getWork();
        this.name = schedule.getName();
        this.password = schedule.getPassword();
        this.date2 = schedule.getDate2();
    }
}
