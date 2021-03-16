package com.spring.boot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)// 테스트 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴 (스프링 부트 테스트와 JUnit 사이에 연결자 역할함
@WebMvcTest(controllers = {HelloController.class} ) // 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc; // 웹 API를 테스트할때 사용

    //@WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴한다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    //@WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

      }
}
