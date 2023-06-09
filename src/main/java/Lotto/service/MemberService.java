package Lotto.service;

import Lotto.domain.MemberDTO;
import Lotto.entity.MemberEntity;
import Lotto.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
       Long saveId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId(); //save: entity 파라미터만 전달가능
//        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
//        Long id = memberRepository.save(memberEntity).getId;
        return saveId;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
        * login.html에서 회원 id, 비번을 받아오고
        * DB로 부터 해당 회원 id의 정보를 가져와서
        * 입력받은 비번과 DB에서 조회한 비번의 일치여부를 판단하여
        * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
         */
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (optionalMemberEntity.isPresent()){
            // 조회 결과가 있다(해당 id를 가진 회원 정보가 있다)
            MemberEntity loginEntity = optionalMemberEntity.get();
            if (loginEntity.getMemberPw().equals(memberDTO.getMemberPw())){
                // 비밀번호일치
                // entity -> DTO 변환 후 리턴
                return MemberDTO.memberDomain(loginEntity);
            }else {
                // 비밀번호 불일치(로그인 실패)
                // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
                return null;
            }
        }else {
            return null;
        }
    }

    public MemberDTO findById(long id){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()){
           // return Member.memberDomain(optionalMemberEntity.get()); // 아래 48 ~ 50 줄과 같은 말
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.memberDomain(memberEntity);
            return memberDTO;
        }else {
            return null;
        }
    }


    public MemberDTO updateForm(String loginId){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(loginId);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.memberDomain(optionalMemberEntity.get());
        }else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
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
