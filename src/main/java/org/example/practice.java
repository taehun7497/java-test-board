package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class practice {
    ArrayList<Article> articleList = new ArrayList<>();

    public void run() {
        Scanner scan = new Scanner(System.in);
        int latestArticleId = 1;

        while (true) {
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
                break;
            } else if (cmd.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();
                Article article = new Article(latestArticleId, title, body);
                articleList.add(article);
                System.out.println("게시물이 등록되었습니다.");
                latestArticleId++;
            } else if (cmd.equals("list")) {
                System.out.println("===================");
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }
            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
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
                Article target = articleList.get(index);
                target.setTitle(newTitle);
                target.setBody(newBody);
                System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
            } else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int inputId = Integer.parseInt(scan.nextLine());
                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물 입니다.");
                    continue;
                }
                articleList.remove(index);
                System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputId);
            } else if (cmd.equals("detail")) {
                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());
                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물 입니다.");
                    continue;
                }
                System.out.println("===================");
                Article article = articleList.get(index);
                System.out.println("번호 : " + article.getId());
                System.out.printf("제목 : %s\n", article.getTitle());
                System.out.printf("내용 : %s\n", article.getBody());
                System.out.println("===================");
            }
        }
    }

    public int findIndexById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == id) {
                return i; // 원하는 것은 찾은 즉시 종료.
            }
        }
        return -1;
    }
}
