package com.sparta.schedule.dto.responsedto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetResponseDto {
    private Long id;
    private String work;
    private String name;
    private String  date1;
    private String  date2;


    public GetResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.work = schedule.getWork();
        this.name = schedule.getName();
        this.date1 = schedule.getDate1();
        this.date2 = schedule.getDate2();
    }

    public GetResponseDto(Long id, String work, String name, String date1, String date2) {
        this.id = id;
        this.work = work;
        this.name =name;
        this.date1 =date1;
        this.date2 = date2;
    }
}
