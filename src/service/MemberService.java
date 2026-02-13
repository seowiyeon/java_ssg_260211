package ll.kor.java.ssg.service;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.repository.MemberRepository;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = Container.memberRepository;
    }

    public void join(String loginId, String loginPw, String name) {
        memberRepository.add(loginId, loginPw, name);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public boolean isLoginIdDup(String loginId) {
        return getMemberByLoginId(loginId) != null;
    }
}
