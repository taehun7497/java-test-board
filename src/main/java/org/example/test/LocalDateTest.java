package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 날짜와 시간을 원하는 형식으로 포맷팅하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        System.out.println("현재 날짜와 시간: " + formattedDateTime);
    }
}
