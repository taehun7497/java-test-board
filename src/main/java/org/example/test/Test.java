import java.util.Scanner;
import java.util.HashMap;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> users = new HashMap<>();
    static HashMap<String, String> nicknames = new HashMap<>();
    static String currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                System.out.println("명령어를 입력해주세요 : ");
            } else {
                System.out.println("명령어를 입력해주세요[" + currentUser + "(" + nicknames.get(currentUser) + ")] : ");
            }
            String command = scanner.nextLine();

            if (command.equals("signup")) {
                signUp();
            } else if (command.equals("login")) {
                login();
            } else if (command.equals("logout")) {
                logout();
            } else if (command.equals("write")) {
                if (currentUser != null) {
                    writePost();
                } else {
                    System.out.println("로그인이 필요합니다.");
                }
            } else if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
    }

    static void signUp() {
        System.out.println("==== 회원 가입을 진행합니다 ====");
        System.out.println("아이디를 입력해주세요 : ");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요 : ");
        String password = scanner.nextLine();
        System.out.println("이름을 입력해주세요 : ");
        String name = scanner.nextLine();

        users.put(id, password);
        nicknames.put(id, name);
        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    static void login() {
        System.out.println("아이디 : ");
        String id = scanner.nextLine();
        System.out.println("비밀번호 : ");
        String password = scanner.nextLine();

        if (users.containsKey(id) && users.get(id).equals(password)) {
            currentUser = id;
            System.out.println(nicknames.get(id) + "님 환영합니다!");
        } else {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
        }
    }

    static void logout() {
        currentUser = null;
        System.out.println("로그아웃 되었습니다.");
    }

    static void writePost() {
        System.out.println("게시글을 작성하세요:");
        String post = scanner.nextLine();
        System.out.println("게시글이 작성되었습니다: " + post);
    }
}
