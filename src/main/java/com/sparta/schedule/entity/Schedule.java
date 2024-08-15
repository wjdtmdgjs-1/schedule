package com.sparta.schedule.entity;

import com.sparta.schedule.dto.requestdto.FixRequestDto;
import com.sparta.schedule.dto.requestdto.GetRequestDto;
import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        this.work = requestDto.getWork();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date1 = simpleDateFormat.format(nowDate);
        this.date2 = simpleDateFormat.format(nowDate);
    }

    public Schedule(GetRequestDto requestDto) {
        this.work = requestDto.getWork();
        this.name = requestDto.getName();
        this.date1 = requestDto.getDate1();
        this.date2 = requestDto.getDate2();
    }

    public Schedule(FixRequestDto requestDto) {
        this.work = requestDto.getWork();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.date2 = requestDto.getDate2();
    }


}


