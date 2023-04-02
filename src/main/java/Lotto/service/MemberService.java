package Lotto.service;

import Lotto.domain.Member;
import Lotto.entity.MemberEntity;
import Lotto.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(MemberEntity.toSaveEntity(member)); //save: entity 파라미터만 전달가능
    }
}
