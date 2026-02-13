package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository extends Repository {

    private List<Member> members;

    public MemberRepository() {
        members = new ArrayList<>();
    }

    public Member add(String loginId, String loginPw, String name) {
        int id = ++lastId;
        Member member = new Member(id, loginId, loginPw, name);
        members.add(member);
        return member;
    }

    public Member findByLoginId(String loginId) {
        for (Member member : members) {
            if (member.loginId.equals(loginId)) {
                return member;
            }
        }
        return null;
    }
}
