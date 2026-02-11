package ll.kor.java.ssg;

import ll.kor.java.ssg.controller.ArticleController;
import ll.kor.java.ssg.controller.MemberController;
import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    List<Article> articles;

    public App() {
        articles = new ArrayList<>();
    }

    void start() {
        IO.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        while (true) {
            IO.print("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 앞뒤에 쓸데없는 공백을 제거

            if (cmd.isEmpty()) continue;
            if (cmd.equals("exit")) break;

            // member 시작
            if (cmd.equals("member join")) memberController.doJoin();

                // Article 시작
            else if (cmd.equals("article write")) articleController.doWrite();
            else if (cmd.startsWith("article list")) articleController.showList(cmd);
            else if (cmd.startsWith("article detail ")) articleController.showDetail(cmd);
            else if (cmd.startsWith("article delete ")) articleController.doDelete(cmd);
            else if (cmd.startsWith("article modify ")) articleController.doModify(cmd);
            else IO.println("존재하지 않는 명령어입니다.");
        }

        IO.println("== 프로그램 끝 ==");
        sc.close();
    }


    private void makeTestData() {
        IO.println("테스트를 위한 데이터를 생성합니다.");

        articles.add(new Article(1, Util.getNowDateStr(), "제목 1", "내용 1", 10));
        articles.add(new Article(2, Util.getNowDateStr(), "제목 2", "내용 2", 43));
        articles.add(new Article(3, Util.getNowDateStr(), "제목 3", "내용 3", 33));
    }
}