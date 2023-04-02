package Lotto.entity;

import Lotto.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // db 관련 객체
@Getter @Setter
@Table(name = "member_table")
public class MemberEntity {

    private static long sequence = 0l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK키 정의
    // @Column(name = "member_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String memberId;

    @Column(length = 20)
    private String memberPw;

    public static MemberEntity toSaveEntity(Member member){ // 도메인에 있는 member 정보를 Entity에 담아서 옮겨 담는다.
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(++sequence);
        memberEntity.setMemberId(member.getMemberId());
        memberEntity.setMemberPw(member.getMemberPw());
        return memberEntity;
    }

}
