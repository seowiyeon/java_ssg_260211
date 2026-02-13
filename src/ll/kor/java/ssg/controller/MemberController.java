package ll.kor.java.ssg.controller;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.service.MemberService;

import java.util.Scanner;

public class MemberController extends Controller {

    private MemberService memberService;
    private Scanner sc;
    private static Member loginedMember;

    public MemberController(Scanner sc) {
        this.sc = sc;
        memberService = Container.memberService;
    }

    @Override
    public void makeTestData() {
        memberService.join("admin", "1234", "관리자");
    }

    @Override
    public void doAction(String cmd, String actionMethodName) {
        switch (actionMethodName) {
            case "join":
                join();
                break;
            case "login":
                login();
                break;
            case "logout":
                logout();
                break;
        }
    }

    private void join() {
        System.out.print("아이디: ");
        String loginId = sc.nextLine();

        if (memberService.isLoginIdDup(loginId)) {
            System.out.println("이미 사용중인 아이디입니다.");
            return;
        }

        System.out.print("비번: ");
        String loginPw = sc.nextLine();

        System.out.print("이름: ");
        String name = sc.nextLine();

        memberService.join(loginId, loginPw, name);
        System.out.println("회원가입 완료");
    }

    private void login() {
        System.out.print("아이디: ");
        String loginId = sc.nextLine();

        System.out.print("비번: ");
        String loginPw = sc.nextLine();

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null || !member.loginPw.equals(loginPw)) {
            System.out.println("로그인 실패");
            return;
        }

        loginedMember = member;
        System.out.println(member.name + "님 환영합니다.");
    }

    private void logout() {
        loginedMember = null;
        System.out.println("로그아웃 완료");
    }

    public static Member getLoginedMember() {
        return loginedMember;
    }
}
