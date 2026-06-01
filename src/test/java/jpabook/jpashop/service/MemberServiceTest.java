package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setUsername("kim");

        //When
        Long saveId = memberService.join(member);

        //Then
        em.flush();
        assertEquals(member, memberService.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setUsername("Kim");

        Member member2 = new Member();
        member2.setUsername("Kim");

        //When
        memberService.join(member1);

        //Then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }

}