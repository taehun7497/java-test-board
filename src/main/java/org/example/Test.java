import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 아이디, 비밀번호, 닉네임을 저장할 Map 생성
        Map<String, String> credentials = new HashMap<>();
        Map<String, String> nicknames = new HashMap<>();
        while (true) {
            System.out.print("명령어를 입력하세요 (signup, login, exit): ");
            String command = scanner.next();
            if (command.equals("signup")) {
                // 회원가입 기능
                System.out.println("\n회원가입을 진행합니다.");
                System.out.print("아이디를 입력하세요: ");
                String username = scanner.next();
                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.next();
                System.out.print("닉네임을 입력하세요: ");
                String nickname = scanner.next();
                // 아이디, 비밀번호, 닉네임을 Map에 저장
                credentials.put(username, password);
                nicknames.put(username, nickname);
                System.out.println("회원가입이 완료되었습니다.\n");
            } else if (command.equals("login")) {
                // 로그인 기능
                System.out.println("\n로그인을 진행합니다.");
                System.out.print("아이디를 입력하세요: ");
                String inputId = scanner.next();
                System.out.print("비밀번호를 입력하세요: ");
                String inputPassword = scanner.next();
                // 입력한 아이디가 Map에 존재하고, 비밀번호가 일치하는지 확인
                if (credentials.containsKey(inputId) && credentials.get(inputId).equals(inputPassword)) {
                    System.out.println("로그인 성공!");
                    System.out.println("닉네임: " + nicknames.get(inputId) + "\n");
                } else {
                    System.out.println("아이디 또는 비밀번호가 잘못되었습니다. 다시 시도해주세요.\n");
                }
            } else if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("올바른 명령어를 입력하세요.\n");
            }
        }
        scanner.close();
    }
}