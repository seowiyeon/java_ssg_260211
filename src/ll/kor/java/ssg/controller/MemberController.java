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
    private Member loginedmember;


    public MemberController(Scanner sc) {
        members = new ArrayList<>();
        this.sc = sc;
        loginedmember = null;
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
            default:
                IO.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    private void doLogin() {
        IO.print("로그인 아이디 : ");
        String loginId = sc.nextLine();
        IO.print("로그인 비번 : ");
        String loginPw = sc.nextLine();

        Member member = getMemberByLoginId(loginId);

        if ( member == null ) {
            IO.println("해당 회원은 존재하지 않습니다.");
            return;
        }

        if ( !member.loginPw.equals(loginPw) ){
            IO.println("비밀번호를 확인해주세요");
            return;
        }

        loginedmember = member;
        IO.println(String.format("로그인 성공! %s님 환영합니다^^", loginId));
    }

    private void doJoin() {
        int id = members.size() + 1;
        String regDate = Util.getNowDateStr();

        String loginId = null;

        while (true) {
            IO.print("로그인 아이디 : ");
            loginId = sc.nextLine();

            if (!isJoinableLoginId(loginId)) {
                IO.println(String.format("%s(은)는 이미 사용중인 아이디 입니다.", loginId));
                continue;
            }

            break;
        }

        String loginPw = null;

        while (true) {
            IO.print("로그인 비번 : ");
            loginPw = sc.nextLine();
            IO.print("로그인 비번확인 : ");
            String loginPwConfirm = sc.nextLine();

            // if ( loginPw.equals(loginPwConfirm) == false ) {
            if (!loginPw.equals(loginPwConfirm)) {
                IO.println("비밀번호를 다시 입력해주세요.");
                continue;
            }

            break;
        }

        Member member = new Member(id, regDate, loginId, loginPw);
        members.add(member);

        IO.println(String.format("%s님 회원가입이 완료되었습니다.", loginId));
    }

    private int getMemberIndexByLoginId(String loginId) {
        int i = 0;

        for (Member member : members) {
            if (member.loginId.equals(loginId)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private Member getMemberByLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);

        if ( index == -1 ) {
            return null;
        }

        return members.get(index);
    }

    private boolean isJoinableLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);

        if (index == -1) {
            return true;
        }

        return false;
    }


}