package Lotto.service;

import Lotto.domain.Member;
import Lotto.entity.MemberEntity;
import Lotto.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(Member member) {
       Long saveId = memberRepository.save(MemberEntity.toSaveEntity(member)).getId(); //save: entity 파라미터만 전달가능
//        MemberEntity memberEntity = MemberEntity.toSaveEntity(member);
//        Long id = memberRepository.save(memberEntity).getId;
        return saveId;
    }

    public Member login(Member member) {
        /*
        * login.html에서 회원 id, 비번을 받아오고
        * DB로 부터 해당 회원 id의 정보를 가져와서
        * 입력받은 비번과 DB에서 조회한 비번의 일치여부를 판단하여
        * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
         */
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(member.getMemberId());
        if (optionalMemberEntity.isPresent()){
            MemberEntity loginEntity = optionalMemberEntity.get();
            if (loginEntity.getMemberPw().equals(member.getMemberPw())){
                return Member.memberDomain(loginEntity);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public Member findById(long id){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()){
           // return Member.memberDomain(optionalMemberEntity.get()); // 아래 48 ~ 50 줄과 같은 말
            MemberEntity memberEntity = optionalMemberEntity.get();
            Member member = Member.memberDomain(memberEntity);
            return member;
        }else {
            return null;
        }
    }

//    public boolean login(Member member) {
//        /*
//         * login.html에서 회원 id, 비번을 받아오고
//         * DB로 부터 해당 회원 id의 정보를 가져와서
//         * 입력받은 비번과 DB에서 조회한 비번의 일치여부를 판단하여
//         * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
//         */
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(member.getMemberId());
//        if (optionalMemberEntity.isPresent()){
//            MemberEntity loginEntity = optionalMemberEntity.get();
//            if (loginEntity.getMemberPw().equals(member.getMemberPw())){
//                return true;
//            }else {
//                return false;
//            }
//        }else {
//            return false;
//        }
//    }
}
