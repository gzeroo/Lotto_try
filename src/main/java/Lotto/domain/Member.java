package Lotto.domain;

import Lotto.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Member {


    private long id;

    private String memberId;
    // private boolean rememberId;

    private String memberPw;

    //private HashMap<String, Member> memberLikeNumber; // 키: 회원id(memberId), 밸류: 회원 지정 번호 리스트


    public Member(){};

    public static Member memberDomain(MemberEntity memberEntity){
        Member member = new Member();
        member.setId(memberEntity.getId());
        member.setMemberId(member.getMemberId());
        member.setMemberPw(member.getMemberPw());
        return member;
    }



}
