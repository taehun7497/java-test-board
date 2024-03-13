//package org.example;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class BoardApp {
//    ArrayList<Article> articleList = new ArrayList<>();
//
//    public void run() {
//        Scanner scan = new Scanner(System.in);
//        // 현재 날짜와 시간 구하기
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        // 날짜와 시간 포맷 지정 (예: "yyyy-MM-dd HH:mm:ss")
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        int latestArticleId = 1; // 시작 번호를 1로 지정
//
//        // 반복 횟수 정할 수 없음 => 무한 반복 구조
//        while (true) { // 반복 조건이 true이기 때문에 무한 반복
//            System.out.print("명령어 : ");
//            String cmd = scan.nextLine();
//
//            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
//                System.out.println("프로그램을 종료합니다.");
//                break; // 반복문 탈출
//
//            } else if (cmd.equals("add")) {
//                System.out.print("게시물 제목을 입력해주세요 : ");
//                String title = scan.nextLine();
//                System.out.print("게시물 내용을 입력해주세요 : ");
//                String body = scan.nextLine();
//
//                // 모든 매개변수를 받는 생성자 이용
//                Article article = new Article(latestArticleId, title, body);
//                articleList.add(article);
//                System.out.println("게시물이 등록되었습니다.");
//                latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
//
//            } else if (cmd.equals("list")) {
//                System.out.println("===================");
//
//                for (int i = 0; i < articleList.size(); i++) {
//                    Article article = articleList.get(i);
//                    System.out.println("번호 : " + article.getId());
//                    System.out.printf("제목 : %s\n", article.getTitle());
//                    System.out.println("===================");
//                }
//            } else if (cmd.equals("update")) {
//                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
//
//                int inputId = Integer.parseInt(scan.nextLine());
//                int index = findIndexById(inputId);
//                if (index == -1) {
//                    System.out.println("없는 게시물입니다.");
//                    continue;
//                }
//
//                System.out.print("새로운 제목을 입력해주세요 : ");
//                String newTitle = scan.nextLine();
//                System.out.print("새로운 내용을 입력해주세요 : ");
//                String newBody = scan.nextLine();
//
//                Article target = articleList.get(index);
//                target.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
//                target.setBody(newBody);
//
//                System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
//            } else if (cmd.equals("delete")) {
//                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
//                int inputId = Integer.parseInt(scan.nextLine()); // 1 -> index : 0
//                int index = findIndexById(inputId);
//                if (index == -1) {
//                    System.out.println("존재하지 않는 게시물입니다.");
//                    continue;
//                }
//                articleList.remove(index);
//
//                System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputId);
//            } else if (cmd.equals("detail")) {
//                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
//                int inputId = Integer.parseInt(scan.nextLine());
//                int index = findIndexById(inputId);
//
//                if (index == -1) {
//                    System.out.println("없는 게시물입니다.");
//                    continue;
//                }
//
//                Article article = articleList.get(index);
//                System.out.println("===================");
//                System.out.println("번호 : " + article.getId());
//                System.out.printf("제목 : %s\n", article.getTitle());
//                System.out.printf("내용 : %s\n", article.getBody());
//
//                // 포맷에 맞게 날짜와 시간 출력
//                String formattedDateTime = currentDateTime.format(formatter);
//                // 조회수 카운트를 세고 출력
//                article.incrementViewCount();
//                int viewCount = article.getViewcount();
//                System.out.println("등록날짜: " + formattedDateTime);
//                System.out.println("조회수: " + article.getViewcount());
//                System.out.println("===================");
//                System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
//
//
////                switch (cmd) {
////                    case "1":
////                        System.out.println("[댓글기능]");
////                        break;
////                    case "2":
////                        System.out.println("[추천기능]");
////                        break;
////                    case "3":
////                        System.out.println("[수정기능]");
////                        break;
////                    case "4":
////                        System.out.println("[삭제기능]");
////                        break;
////                    case "5":
////                        System.out.println("[목록으로]");
////                        break;
////                    default:
////                        System.out.println("잘못된 명령입니다.");
////                }
//
//            } else if (cmd.equals("search")) {
//                System.out.print("검색 키워드를 입력 해주세요 : ");
//                String keyword = scan.nextLine();
//                boolean found = false; // 검색 결과가 있는지 여부를 나타내는 변수
//                System.out.println("===================");
//                // articleList를 순회하며 검색 키워드가 제목에 포함된 게시물을 출력
//                for (Article article : articleList) {
//                    if (article.getTitle().contains(keyword)) {
//                        // 검색 결과가 있으면 출력
//                        System.out.println("번호 : " + article.getId());
//                        System.out.printf("제목 : %s\n", article.getTitle());
//                        System.out.printf("내용 : %s\n", article.getBody());
//                        System.out.println("===================");
//                        found = true;
//                    }
//                }
//                // 검색 결과가 없을 경우 안내 메시지 출력
//                if (!found) {
//                    System.out.println("===================");
//                    System.out.println("검색 결과가 없습니다.");
//                    System.out.println("===================");
//                }
//            }
//        }
//    }
//
//    public int findIndexById(int id) {
//        for (int i = 0; i < articleList.size(); i++) {
//            Article article = articleList.get(i);
//            if (article.getId() == id) {
//                return i; // 원하는 것은 찾은 즉시 종료.
//            }
//        }
//        return -1;
//    }
//}