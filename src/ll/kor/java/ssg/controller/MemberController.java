package ll.kor.java.ssg.controller;

import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private List<Member> members;
    private Scanner sc;
    private String cmd;
    private Member loginedMember;

    public MemberController(Scanner sc) {
        members = new ArrayList<>();
        this.sc = sc;
        loginedMember = null;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                IO.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void makeTestData() {
        IO.println("테스트를 위한 회원 데이터를 생성합니다.");

        members.add(new Member(1, Util.getNowDateStr(), "admin", "admin", "관리자"));
        members.add(new Member(2, Util.getNowDateStr(), "user1", "user1", "홍길동"));
        members.add(new Member(3, Util.getNowDateStr(), "user2", "user2", "홍길순"));
    }

    private void doJoin() {

        if (loginedMember != null) {
            IO.println("로그아웃 후 이용해주세요.");
            return;
        }

        int id = members.size() + 1;
        String regDate = Util.getNowDateStr();

        String loginId;

        while (true) {
            IO.print("로그인 아이디 : ");
            loginId = sc.nextLine();

            if (!isJoinableLoginId(loginId)) {
                IO.println(loginId + "(은)는 이미 사용중인 아이디 입니다.");
                continue;
            }
            break;
        }

        String loginPw;

        while (true) {
            IO.print("로그인 비번 : ");
            loginPw = sc.nextLine();
            IO.print("로그인 비번확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (!loginPw.equals(loginPwConfirm)) {
                IO.println("비밀번호를 다시 입력해주세요.");
                continue;
            }
            break;
        }

        IO.print("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        members.add(member);

        IO.println(name + "님 회원가입이 완료되었습니다.");
    }

    private void doLogin() {

        if (loginedMember != null) {
            IO.println("이미 로그인 상태입니다.");
            return;
        }

        IO.print("로그인 아이디 : ");
        String loginId = sc.nextLine();

        IO.print("로그인 비번 : ");
        String loginPw = sc.nextLine();

        Member member = getMemberByLoginId(loginId);

        if (member == null) {
            IO.println("해당 회원은 존재하지 않습니다.");
            return;
        }

        if (!member.loginPw.equals(loginPw)) {
            IO.println("비밀번호를 확인해주세요.");
            return;
        }

        loginedMember = member;
        IO.println(member.name + "님 환영합니다^^");
    }

    private void doLogout() {

        if (loginedMember == null) {
            IO.println("현재 로그인 상태가 아닙니다.");
            return;
        }

        IO.println(loginedMember.name + "님 로그아웃 되었습니다.");
        loginedMember = null;
    }

    private int getMemberIndexByLoginId(String loginId) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).loginId.equals(loginId)) {
                return i;
            }
        }
        return -1;
    }

    private Member getMemberByLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);
        if (index == -1) return null;
        return members.get(index);
    }

    private boolean isJoinableLoginId(String loginId) {
        return getMemberIndexByLoginId(loginId) == -1;
    }

    // 로그인 상태 확인용
    public boolean isLogined() {
        return loginedMember != null;
    }

    public Member getLoginedMember() {
        return loginedMember;
    }
}
