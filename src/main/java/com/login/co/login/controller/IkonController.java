package com.login.co.login.controller;

import com.login.co.login.service.IkonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ikon")
@RequiredArgsConstructor
public class IkonController {

    private final IkonService ikonService;

    @GetMapping("/getData/{size}/{page}")
    public Object responseIkon(@PathVariable("size") Integer size, @PathVariable ("page") Integer page){
        return ikonService.GetDataIkonService(size, page);
    }
}
