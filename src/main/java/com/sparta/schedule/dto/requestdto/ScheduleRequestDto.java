package com.sparta.schedule.dto.requestdto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ScheduleRequestDto {
    private String work;
    private String name;
    private String password;
    private String date1;
    private String date2;

}
