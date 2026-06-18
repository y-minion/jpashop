package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
