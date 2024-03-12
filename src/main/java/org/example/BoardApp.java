package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>();

    public void run() {
        Scanner scan = new Scanner(System.in);
        // 현재 날짜와 시간 구하기
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 날짜와 시간 포맷 지정 (예: "yyyy-MM-dd HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int latestArticleId = 1; // 시작 번호를 1로 지정

        // 반복 횟수 정할 수 없음 => 무한 반복 구조
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출

            } else if (cmd.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();

                // 모든 매개변수를 받는 생성자 이용
                Article article = new Article(latestArticleId, title, body);
                articleList.add(article);
                System.out.println("게시물이 등록되었습니다.");
                latestArticleId++; // 게시물이 생성될 때마다 번호를 증가

            } else if (cmd.equals("list")) {
                System.out.println("===================");

                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }
            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");

                int inputId = Integer.parseInt(scan.nextLine());
                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();
                System.out.print("새로운 내용을 입력해주세요 : ");
                String newBody = scan.nextLine();

                // 변하지 않는 것에서 변하는 것을 분리
                // 변하는 것에서 변하지 않는 것을 분리
//                int index = findIndexById(inputId);
                Article target = articleList.get(index);
                target.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
                target.setBody(newBody);

                System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
            } else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine()); // 1 -> index : 0
                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("존재하지 않는 게시물입니다.");
                    continue;
                }
                articleList.remove(index);

                System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputId);
            } else if (cmd.equals("detail")) {
                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());
                int index = findIndexById(inputId);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                Article article = articleList.get(index);
                System.out.println("===================");
                System.out.println("번호 : " + article.getId());
                System.out.printf("제목 : %s\n", article.getTitle());
                System.out.printf("내용 : %s\n", article.getBody());

                // 포맷에 맞게 날짜와 시간 출력
                String formattedDateTime = currentDateTime.format(formatter);
                System.out.println("등록날짜: " + formattedDateTime);
                System.out.println("===================");

            } else if (cmd.equals("list2")) {

                // 테스트 데이터
                articleList.add(new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", ""));
                articleList.add(new Article(2, "자바 질문좀 할게요~", ""));
                articleList.add(new Article(3, "정처기 따야되나요?", ""));
                System.out.println("===================");

                for (Article article : articleList) {
                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }
            }
        }
    }

    // 입력 : 찾고자 하는 게시물 번호
// 출력 : 게시물 번호에 해당하는 인덱스
    public int findIndexById(int id) {
//        int index = -1; // 최종적으로 찾은 목적 인덱스를 저장하기 위함. index는 0 ~ n까지 표현 가능하므로 인덱스로 사용이 불가능한 -1로 설정

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == id) {
                return i; // 원하는 것은 찾은 즉시 종료.
            }
        }
        return -1;
    }
}