package com.sparta.schedule.repository;

import com.sparta.schedule.dto.requestdto.DeleteRequestDto;
import com.sparta.schedule.dto.requestdto.FixRequestDto;
import com.sparta.schedule.dto.responsedto.GetResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleRepository {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (work, name, password, date1, date2) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getWork());
                    preparedStatement.setString(2, schedule.getName());
                    preparedStatement.setString(3, schedule.getPassword());
                    preparedStatement.setString(4, String.valueOf(schedule.getDate1()));
                    preparedStatement.setString(5, String.valueOf(schedule.getDate2()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public void fix(FixRequestDto requestDto) {
        //비번맞는지 체크!
        pwCheck(requestDto.getId(), requestDto.getPassword());

        //수정하는 순간의 시간을 받아 형식에 맞게 format
        java.util.Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date3 = simpleDateFormat.format(nowDate);

        String sql = "UPDATE schedule SET work = ?, name = ?, date2 = ?  WHERE id = ? AND password =?";

        jdbcTemplate.update(sql, requestDto.getWork(), requestDto.getName(), date3,
                requestDto.getId(), requestDto.getPassword());

    }

    public void delete(DeleteRequestDto requestDto) {
        //비번 맞는지 체크하기!
        pwCheck(requestDto.getId(), requestDto.getPassword());
        String sql = "DELETE FROM schedule WHERE id = ? AND password = ?";
        jdbcTemplate.update(sql, requestDto.getId(), requestDto.getPassword());
    }

    public List<GetResponseDto> find(String date2, String name) {

        //nullExeption을 피하기위해 equals.() 대신 != 로 처리
        if ((date2 != null) && (name != null)) {
            String sql = "SELECT * FROM schedule WHERE DATE_FORMAT(date2, '%Y-%m-%d')= ? AND name = ? ORDER BY date2 DESC";

            return jdbcTemplate.query(sql, new RowMapper<GetResponseDto>() {
                @Override
                public GetResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String work = rs.getString("work");
                    String name = rs.getString("name");
                    String date1 = rs.getString("date1");
                    String date2 = rs.getString("date2");
                    return new GetResponseDto(id, work, name, date1, date2);
                }
            }, date2, name);

        } else if ((date2 == null) && (name != null)) {
            String sql = "SELECT * FROM schedule WHERE name = ? ORDER BY date2 DESC";

            return jdbcTemplate.query(sql, new RowMapper<GetResponseDto>() {
                @Override
                public GetResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String work = rs.getString("work");
                    String name = rs.getString("name");
                    String date1 = rs.getString("date1");
                    String date2 = rs.getString("date2");
                    return new GetResponseDto(id, work, name, date1, date2);
                }
            }, name);
        } else if ((date2 != null) && (name == null)) {
            String sql = "SELECT * FROM schedule WHERE DATE_FORMAT(date2, '%Y-%m-%d')= ? ORDER BY date2 DESC";

            return jdbcTemplate.query(sql, new RowMapper<GetResponseDto>() {
                @Override
                public GetResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String work = rs.getString("work");
                    String name = rs.getString("name");
                    String date1 = rs.getString("date1");
                    String date2 = rs.getString("date2");
                    return new GetResponseDto(id, work, name, date1, date2);
                }
            }, date2);
        } else {
            String sql = "SELECT * FROM schedule ORDER BY date2 DESC";

            return jdbcTemplate.query(sql, new RowMapper<GetResponseDto>() {
                @Override
                public GetResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String work = rs.getString("work");
                    String name = rs.getString("name");
                    String date1 = rs.getString("date1");
                    String date2 = rs.getString("date2");
                    return new GetResponseDto(id, work, name, date1, date2);
                }
            });
        }

    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setWork(resultSet.getString("work"));
                schedule.setName(resultSet.getString("name"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setDate1(resultSet.getString("date1"));
                schedule.setDate2(resultSet.getString("date2"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    public boolean pwCheck(long id, String password){
        //id에 해당하는 비번 값 찾음
        String sql = "SELECT password FROM schedule WHERE id = ?";
        //database에 있는 pw값 가지고오기
        String pw = jdbcTemplate.queryForObject(sql,String.class, id);
        //맞는지 체크!
        if(pw.equals(password)){
            return true;
        }else{
            throw new IllegalArgumentException("비번 틀림.");
        }

    }
}

