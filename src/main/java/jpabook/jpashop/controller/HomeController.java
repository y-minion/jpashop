package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //롬복을 쓰면 해당 어노테이션으로 쉽게 로거를 출력할 수 있다.
public class HomeController {

    @RequestMapping("/")
    public String home() {

        log.info("home controller");
        return "home"; //매핑 메서드의 반환값(문자열)에 따라 해당하는 html문서로 찾아가서 화면에 렌더링이 된다.
    }
}
