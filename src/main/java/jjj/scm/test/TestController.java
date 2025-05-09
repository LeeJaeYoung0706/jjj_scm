package jjj.scm.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController implements TestSwaggerController {

    @GetMapping("/1")
    @Override
    public String test1(TestDto dto) {
        return dto.getUserId();
    }

}
