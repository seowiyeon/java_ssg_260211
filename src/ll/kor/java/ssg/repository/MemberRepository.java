package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository extends Repository {
    public List<Member> members;

    public MemberRepository() {
        members = new ArrayList<>();
    }

    public void add(Member member) {
        members.add(member);
        lastId++;
    }
}