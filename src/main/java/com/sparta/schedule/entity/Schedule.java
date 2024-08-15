package com.sparta.schedule.entity;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String work;
    private String name;
    private String password;
    private String date1;
    private String date2;


    public Schedule(ScheduleRequestDto requestDto) {
        this.name = requestDto.getName();
        this.work = requestDto.getWork();
        this.password = requestDto.getPassword();
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date1 = simpleDateFormat.format(nowDate);
        this.date2 = simpleDateFormat.format(nowDate);
    }

}


