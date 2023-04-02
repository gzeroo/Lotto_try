package Lotto.domain;

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


//    public Member(String memberId, String memberPw){
//        this.memberId = memberId;
//        this.memberPw = memberPw;
//    }

}
