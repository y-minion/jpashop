package jpabook.jpashop.repository;


import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        }else{ //save함수에 있고, regacy방식은 merge방식인데, 이걸 변경감지 방식으로 리팩토링 해보자.
            //ID가 있을때 실행되는 분기이기 때문에 update때 사용이 가능하다.
            em.merge(item);

            /**
            Item findItem = em.find(Item.class, item.getId());//근데 하위 클래스인 Book 이 올텐데 상위 클래스를 지정해도 조회가 가능한가? (이 부분 질문)
            findItem
             */

        }

    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();


    }
}
