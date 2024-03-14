package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수
    Scanner scan = new Scanner(System.in);
    Map<String, String> credentials = new HashMap<>();
    Map<String, String> nicknames = new HashMap<>();
    String loggedInId = null;
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정
    int WRONG_VALUE = -1; // 값의 의미를 부여

    public void run() {
        makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            } switch (cmd) {
                case "add" -> add();
                case "list" -> list();
                case "update" -> update();
                case "delete" -> delete();
                case "detail" -> detail();
                case "search" -> search();
                case "signup" -> signup();
                case "login" -> login();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    private void login() {
        // 로그인 기능
        System.out.println("\n로그인을 진행합니다.");
        System.out.print("아이디를 입력하세요: ");
        String inputId = scan.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String inputPassword = scan.nextLine();
        // 입력한 아이디가 Map에 존재하고, 비밀번호가 일치하는지 확인
        if (credentials.containsKey(inputId) && credentials.get(inputId).equals(inputPassword)) {
            String nickname = nicknames.get(inputId);
            loggedInId = inputId;
            System.out.println(nickname + "님 환영합니다!");
            System.out.println("명령어를 입력해주세요[" + inputId + "(" + nickname + ")]\n");

        } else {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.\n");
        }
    }

    private void signup() {
        System.out.println("==== 회원 가입을 진행합니다 ====");
        System.out.print("아이디를 입력해주세요 : ");
        String username = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = scan.nextLine();
        // 아이디, 비밀번호, 닉네임을 Map에 저장
        credentials.put(username, password);
        nicknames.put(username, nickname);
        System.out.println("\n==== 회원가입이 완료되었습니다. ====");
    }

    private void search() {
        // 검색어를 입력
        System.out.println("검색 키워드를 입력해주세요 :");
        String keyword = scan.nextLine();
        ArrayList<Article> searchedList = new ArrayList<>();
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }
        printArticleList(searchedList);
    }

    private void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }
        article.increaseHit();
        // 조회수 증가 v2
        article.increaseHit(); // 각 객체들이 알아서 조회수를 증가시킴. 관련 로직은 한 곳에 집중시켜서 관리를 편하게 함
        System.out.printf("======%d번 게시물======\n", article.getId());
        System.out.println("번호 : " + article.getId());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
        System.out.println("등록날짜 : " + article.getRegDate());
        System.out.println("조회수 : " + article.getHit());
        System.out.println("===================");
        System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("[댓글등록]");
                System.out.print("댓글 내용 : ");
                String comment = scanner.nextLine();
                // 댓글 등록하는 코드
                System.out.println("댓글이 성공적으로 등록되었습니다.");
                System.out.println("등록날짜 : " + article.getRegDate());
                break;
            case 2:
                System.out.println("[추천]");
                break;
            case 3:
                System.out.println("[수정]");
                break;
            case 4:
                System.out.println("[삭제]");
                break;
            case 5:
                System.out.println("[목록으로]");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }
        if (choice == 5) {
            return;
        }
    }
    private void delete() {
        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = findArticleById(inputId);
        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }
        articleList.remove(article); // arrayList remove는 값을 직접 찾아서 지워주기도 함
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }
    private void update() {
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = findArticleById(inputId);
        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }
        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();
        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();
        article.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
        article.setBody(newBody);
        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }
    private void list() {
        printArticleList(this.articleList);  // 전체 출력
    }
    private void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();
        // 모든 매개변수를 받는 생성자 이용
        Article article = new Article(latestArticleId, title, body, 0, getCurrentDateTime());
        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");
        latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
    }
    private void makeTestData() {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }
    public void printArticleList(ArrayList<Article> targetList) {
        System.out.println("===================");
        for (int i = 0; i < targetList.size(); i++) {
            Article article = targetList.get(i);
            System.out.println("번호 : " + article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.println("===================");
        }
    }
    public Article findArticleById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return article; // 원하는 것은 찾은 즉시 종료.
            }
        }
        return null; // 객체타입에서만 사용 가능
    }
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        // 날짜와 시간의 형식을 지정합니다. 여기서는 연-월-일 시:분:초 형식을 사용합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        // 지정한 형식으로 날짜와 시간을 출력합니다.
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    private int getParamAsInt(String param, int defaultValue) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
}