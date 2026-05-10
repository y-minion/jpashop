package jpabook.jpashop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member2 {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
}
