package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class BoardApp {
    ArticleRepository articleRepository = new ArticleRepository();
    Scanner scan = new Scanner(System.in);
    static HashMap<String, String> users = new HashMap<>();
    static HashMap<String, String> nicknames = new HashMap<>();
    static String currentUser = null;
    String loggedInId = null;

    public void run() {
        articleRepository.makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            if (currentUser == null) {
                System.out.print("명령어를 입력해주세요 : ");
            } else {
                System.out.print("명령어를 입력해주세요[" + currentUser + "(" + nicknames.get(currentUser) + ")] : ");
            }
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            }

            switch (cmd) {
                case "add" -> articleRepository.add();
                case "list" -> articleRepository.list();
                case "update" -> articleRepository.update();
                case "delete" -> articleRepository.delete();
                case "detail" -> articleRepository.detail();
                case "search" -> articleRepository.search();
                case "signup" -> articleRepository.signup();
                case "login" -> articleRepository.login();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }
}