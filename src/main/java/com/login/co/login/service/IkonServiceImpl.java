package com.login.co.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class IkonServiceImpl implements IkonService{


    private final RestTemplate restTemplate;

    @Override
    public Object GetDataIkonService() {
        try {
            String url = "https://jsonplaceholder.typicode.com/posts";
            Object objects = restTemplate.getForObject(url, Object.class);

            // TODO : get object value
            // mapping di dto
            // add into list <dto>
            // pagination
            // Pageable pageable =
                    //Page<User> pages = new PageImpl<User>(users, pageable, users.size());
            // if page not null ?
            // return data by page request
            // return data not found
            return objects;
        }catch (Exception e){
            //
            return null; // just for temp
        }
    }
}
