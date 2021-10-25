package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // hello 라는 url로 오면 이 컨트롤러가 호출됨
    @GetMapping("hello")
    public String hello(Model model) {
        // 값을 담기
        model.addAttribute("data", "hello!!!");
        return "hello";
    }
}
