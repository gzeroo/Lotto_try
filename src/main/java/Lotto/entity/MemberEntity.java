package Lotto.entity;

import Lotto.domain.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // db 관련 객체
@Getter @Setter
@Table(name = "member_table")
public class MemberEntity {

    @Id // PK키 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 는 db 네가 스스로 정해라
    @Column(name = "member_num")
    private Long id;

    @Column(length = 10, unique = true)
    private String memberId;

    @Column(length = 20)
    private String memberPw;

    public static MemberEntity toSaveEntity(MemberDTO memberDTO){ // 도메인에 있는 memberDTO 정보를 Entity에 담아서 옮겨 담는다.
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPw(memberDTO.getMemberPw());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){ // 도메인에 있는 memberDTO 정보를 Entity에 담아서 옮겨 담는다.
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId()); // 계정이 생성된 상태라 생성된 계정 고유 넘버 가져오기
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPw(memberDTO.getMemberPw());
        return memberEntity;
    }

}
