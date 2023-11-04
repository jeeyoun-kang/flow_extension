package com.example.flow.controller;

import com.example.flow.entity.Extension;
import com.example.flow.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class flowcontroller {

    private final ExtensionRepository extensionRepository;


    @GetMapping("/")
    public String homeController(){
        return "main";
    }

    @ResponseBody
    @PostMapping("/count")
    public int count(@RequestParam("count") int count, @RequestParam("text") String name){
        //만약 기존 데베에 있는 값이 들어왔다면 무조건 삭제
        Extension existingMember = extensionRepository.findByName(name);

        if (existingMember != null) {
            // 이름이 일치하는 멤버가 이미 존재하면 삭제
            extensionRepository.delete(existingMember);
        } else {
            // 새로운 멤버 추가
            Extension newExtension = new Extension();
            newExtension.setName(name);
            extensionRepository.save(newExtension);
        }

        return count;
    }

}
