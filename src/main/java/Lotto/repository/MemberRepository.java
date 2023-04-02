package Lotto.repository;

import Lotto.domain.Member;
import Lotto.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // DB에 날릴 쿼리문 : select * from mem_table where memberId = ?
    // 리턴 타입: MemberEntity
    // 매개 변수: memberId(String)
    Optional<MemberEntity> findByMemberId(String memberId); // findByMemberId : jpa 룰 이용해서 생성

}
