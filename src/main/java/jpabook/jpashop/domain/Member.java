package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity //JPA에서 관리하는 테이블임을 선언한다. JPA야 이 클래스를 테이블로 관리해줘.
//이 어노테이션 덕분에 해당 클래스는 DB에서 테이블로 만들어진다. -> 개꿀
// 그래서 이 어노테이션덕분에 JPA가 앱 시작할때 자동으로 해당 클래스에 해당하는 SQL문법을 통해 테이블을 생성. -> 이거는 DDL-auto옵션이 켜져있어야 자동으로 생성해준다.
@Getter @Setter
public class Member {
    //이 클래스 하나가 DB의 MEMBER테이블 전체를 담당한다!
    @Id //해당 칼럼이 PK임을 지정해주는 어노테이션
    @GeneratedValue //값 자동생성 어노테이션 -> 이 어노테이션 덕분에 개발자가 id는 굳이 입력 안해도 된다. 이것덕분에 레코드가 생성되도 값이 절대 겹치지않는다.
    @Column(name = "member_id")
    private Long id;
    private String username; // 그냥 일반 컬럼

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}


