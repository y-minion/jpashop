package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired //해당 리포지토리는 스프링이 넣어줄 수 있도록 오토와이어를 통해 의존성을 주입받는다.
    MemberRepository memberRepository;


    @Test
    @Transactional //왜 트렌젝셔널이 꼭 필요할까?
    @Rollback(value = false)
    public void testMember() {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        Assertions.assertThat(findMember).isEqualTo(member); // 당연히 같아야하는거 아닌가..? ,같은 영속성 컨텍스트 뭐시기 하던데...

    }
}