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
            String cmd = sc.nextLine().trim();

            if (cmd.isEmpty()) continue;
            if (cmd.equals("exit")) break;

            String[] cmdBits = cmd.split(" ");
            if (cmdBits.length == 1) {
                IO.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = cmdBits[0];
            String actionMethodName = cmdBits[1];

            Controller controller = null;

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                IO.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String actionName = controllerName + "/" + actionMethodName;

            // 🔥 로그인 필요한 명령어
            switch (actionName) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if (MemberController.getLoginedMember() == null) {
                        IO.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            // 🔥 로그아웃 상태에서만 가능한 명령어
            switch (actionName) {
                case "member/join":
                case "member/login":
                    if (MemberController.getLoginedMember() != null) {
                        IO.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);
        }

        sc.close();
        IO.println("== 프로그램 끝 ==");
    }
}
