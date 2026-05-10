package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

//리포지토리는 엔티티를 찾아주는 애라고 생각.
@Repository
public class MemberRepository {

    //jpa를 쓰면 엔티티 메니저가 필요하다는데???
    @PersistenceContext //이게 엔티티 매니저를 주입한다는데?? -> 스프링 부트가 다 등록해준다?
    EntityManager em;
    /*
    * # EntityManager란?
    * 자바(개발자)가 JPA로 DB작업을 하기 위해서는 반드시 필요한 존재!!!!
    * JPA는 앞에서 자바 클래스로만 객체지향에 집중해서 DB작업을 할 수있다고 배웠다.
    * 하지만 이 작업은 그냥 이뤄지지 않는다. 모두 엔티티 매니저가 중간에서 알맞게 변형해서 전달해줘서 가능한 것이다.
    * 그러면? DB접근을 다루는 @repository에서는 엔티티매니저가 반드시 있어야한다.
    * persist() -> INSERT(저장)
    * find() -> SELECT(조회)
    * remove() -> DELETE(삭제)
    * merge() -> UPDATE(수정)
    *
    *
    * @PersistenceContext덕분에 해당 엔티티매니저는 모든 사용자들의 요청에 대해 프록시로 연결해서 각각 다른 매니저로 연결한다.
    * 같은 매니저를 공유하면 DB에 반영되기 전에 다른 사용자가 기존에 있던 작업과 충돌이 발생할 수 있다. 최대한 격리하기 위해 독립성을 유지할 수 있게 한다.
    * */


    //왜 member를 반환하지 않고 굳이 id를 반환할까?
    //--> save같은 메서드는 사이드 이펙트를 유발할 수 있는 로직이다. 왠만하면 반환값을 반환하지 않지만, id같이 간단한 정보는 반환해서 다시 찾을 수 있도록 한다.
    public Long save(Member member) {

        //저장할때는 어떻게 저장하려는 테이블을 찾는거지? 테이블을 알아야 레코드를 넣지않나?
        em.persist(member);
        return member.getId();
    }

    //GET하는 메서드인것같은데...
    public Member find(Long id) {
        //어떻게 테이블을 찾지?
        return em.find(Member.class, id);
    }


}
