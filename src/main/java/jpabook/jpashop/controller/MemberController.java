package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); // 왜 폼을 모델에 넣어저 전달할까?
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid MemberForm form) { //@Valide 어노테이션만 사용하면 뷰의 폼에서 전달한 폼의 값들에 대해 폼 클래스에서 적용한 어노테이션에 대한 유효성 검사를 해주나?

        //뷰에서 post로 보낸 폼의 데이터를 꺼내 객체로 만든다.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

    }
}
