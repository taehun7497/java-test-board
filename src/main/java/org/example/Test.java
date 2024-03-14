//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class CommandProcessor {
//    private Map<String, String> credentials = new HashMap<>();
//    private Map<String, String> nicknames = new HashMap<>();
//    private Scanner scanner = new Scanner(System.in);
//    private String loggedInId;
//    private boolean loggedIn;
//
//    public void addUser(String username, String password, String nickname) {
//        credentials.put(username, password);
//        nicknames.put(username, nickname);
//    }
//
//    public void login() {
//        System.out.println("\n로그인을 진행합니다.");
//        System.out.print("아이디를 입력하세요: ");
//        String inputId = scanner.nextLine();
//        System.out.print("비밀번호를 입력하세요: ");
//        String inputPassword = scanner.nextLine();
//
//        if (credentials.containsKey(inputId) && credentials.get(inputId).equals(inputPassword)) {
//            String nickname = nicknames.get(inputId);
//            loggedInId = inputId;
//            loggedIn = true;
//            System.out.println(nickname + "님 환영합니다!");
//        } else {
//            System.out.println("로그인에 실패하였습니다.");
//        }
//    }
//
//    public void processCommands() {
//        while (true) {
//            if (!loggedIn) {
//                System.out.print("(명령어): ");
//            } else {
//                System.out.print("명령어를 입력해주세요[" + loggedInId + "(" + nicknames.get(loggedInId) + ")]: ");
//            }
//            String cmd = scanner.nextLine();
//
//            if (!loggedIn) {
//                if (cmd.equals("exit")) {
//                    System.out.println("프로그램을 종료합니다.");
//                    break;
//                } else if (cmd.equals("signup")) {
//                    signup();
//                } else if (cmd.equals("login")) {
//                    login();
//                } else {
//                    System.out.println("로그인이 필요한 명령어입니다.");
//                }
//            } else {
//                if (cmd.equals("exit")) {
//                    System.out.println("프로그램을 종료합니다.");
//                    break;
//                } else if (cmd.equals("add")) {
//                    add();
//                } else if (cmd.equals("list")) {
//                    list();
//                } else if (cmd.equals("update")) {
//                    update();
//                } else if (cmd.equals("delete")) {
//                    delete();
//                } else if (cmd.equals("detail")) {
//                    detail();
//                } else if (cmd.equals("search")) {
//                    search();
//                } else if (cmd.equals("logout")) {
//                    logout();
//                } else {
//                    System.out.println("올바른 명령어를 입력해주세요.");
//                }
//            }
//        }
//    }
//
//    private void add() {
//        System.out.println("Add 기능을 실행합니다.");
//    }
//
//    private void list() {
//        System.out.println("List 기능을 실행합니다.");
//    }
//
//    private void update() {
//        System.out.println("Update 기능을 실행합니다.");
//    }
//
//    private void delete() {
//        System.out.println("Delete 기능을 실행합니다.");
//    }
//
//    private void detail() {
//        System.out.println("Detail 기능을 실행합니다.");
//    }
//
//    private void search() {
//        System.out.println("Search 기능을 실행합니다.");
//    }
//
//    private void signup() {
//        System.out.println("Signup 기능을 실행합니다.");
//    }
//
//    private void logout() {
//        loggedIn = false;
//        System.out.println("로그아웃 되었습니다.");
//    }
//
//    public static void main(String[] args) {
//        CommandProcessor processor = new CommandProcessor();
//        // 사용자 정보 추가
//        processor.addUser("user1", "password1", "사용자1");
//        processor.addUser("user2", "password2", "사용자2");
//
//        processor.processCommands();
//    }
//}
