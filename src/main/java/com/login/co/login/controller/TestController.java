package com.login.co.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/list")
    public String getList(){
        return "Selamat Datang";
    }

    @GetMapping("/linier/{target}")
    public Integer dataArray(@PathVariable("target") Integer target){
        Integer []intArr = {10,20,30,40,50};
        int pos =-1;
        for(int i=0; i<intArr.length; i++){
            if(intArr[i].equals(target)){
                pos = i;
                break;
            }
        }
        return pos;
    }

//    @GetMapping("/binary/{target}")
//    public Integer binarySearch(@PathVariable("target") Integer target){
//        Integer []intArr = {10,20,30,40,50};
//        int low =0;
//        int high = intArr.length-1;
//        while (low <= high){
//            int mid = low + (high-low) / 2;
//            if()
//        }
//    }
}
