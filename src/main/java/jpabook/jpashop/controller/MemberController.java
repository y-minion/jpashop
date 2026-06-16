package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); // 왜 폼을 모델에 넣어저 전달할까?
        return "members/createMemberForm";
    }

    //POST를 통해 폼을 받으면 폼에서 데이터를 꺼내 객체를 만들어준뒤 멤버 서비스에 저장하고 홈으로 리다이렉트를 시켜준다.
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) { //@Valide 어노테이션만 사용하면 뷰의 폼에서 전달한 폼의 값들에 대해 폼 클래스에서 적용한 어노테이션에 대한 유효성 검사를 해주나?

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        //뷰에서 post로 보낸 폼의 데이터를 꺼내 객체로 만든다.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setUsername(form.getName());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);
        return "members/memberList";
    }
}
