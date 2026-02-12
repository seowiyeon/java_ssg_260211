package ll.kor.java.ssg;

import ll.kor.java.ssg.controller.ArticleController;
import ll.kor.java.ssg.controller.Controller;
import ll.kor.java.ssg.controller.MemberController;

import java.util.Scanner;

public class App {
    void start() {
        IO.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        memberController.makeTestData();

        ArticleController articleController = new ArticleController(sc);
        articleController.makeTestData();

        while (true) {
            IO.print("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 앞뒤에 쓸데없는 공백을 제거

            if (cmd.isEmpty()) continue;
            if (cmd.equals("exit")) break;

            String[] cmdBits = cmd.split(" "); // article detail 1
            if (cmdBits.length == 1) {
                IO.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = cmdBits[0]; // article / member
            String actionMethodName = cmdBits[1]; // detail / join

            Controller controller = null;

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                IO.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
        }

        sc.close();
        IO.println("== 프로그램 끝 ==");
    }
}