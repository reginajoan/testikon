package com.login.co.login.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.co.login.dto.Ikon;
import com.login.co.login.dto.IkonResponse;
import com.login.co.login.exception.DataNotFoundException;
import com.login.co.login.exception.InvalidPaginationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IkonServiceImpl implements IkonService{


    private final RestTemplate restTemplate;

    @Override
    public Object GetDataIkonService(int size, int page) {
        try {
            if (page < 1 || size < 1) {
                throw new InvalidPaginationException("Page and page size must be greater than 0");
            }
            String url = "https://jsonplaceholder.typicode.com/posts";
            Object objects = restTemplate.getForObject(url, Object.class);

            // return data not found
            if(Objects.isNull(objects)){
                throw new DataNotFoundException("Data Not Found");
            }
            // TODO : get object value
            // mapping di dto
            // add into list <dto>
            List<Ikon> responseList = mapObjectToPostList(objects);
            // convert Ikon into IkonResponse
            List<IkonResponse> ikonResponses = convertIkonToIkonResponse(responseList);
            // pagination
            int fromIndex = (page - 1) * size;
            int toIndex = Math.min(fromIndex + size, ikonResponses.size());

            if(fromIndex > ikonResponses.size()){
                throw new InvalidPaginationException("Page out of range");
            }
            // Pageable pageable =
                    //Page<User> pages = new PageImpl<User>(users, pageable, users.size());
            // if page not null ?
            // return data by page request
            return ikonResponses.subList(fromIndex, toIndex);
        }catch (InvalidPaginationException | DataNotFoundException ex) {
            throw ex;
        }catch (Exception e){
            //
            throw new RuntimeException("Error ", e);
        }
    }

    private List<Ikon> mapObjectToPostList(Object postsObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert Object to JSON string and map to List<Post>
            String json = objectMapper.writeValueAsString(postsObject);
            return objectMapper.readValue(json, new TypeReference<List<Ikon>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to map object to Post list", e);
        }
    }

    private List<IkonResponse> convertIkonToIkonResponse(List<Ikon> ikons){
        List<IkonResponse> ikonResponses = new ArrayList<>();
        for(Ikon ikon : ikons){
            IkonResponse ikonResponse = new IkonResponse();
            ikonResponse.setId(ikon.getId());
            ikonResponse.setTitle(ikon.getTitle());
            ikonResponses.add(ikonResponse);
        }
        return ikonResponses;
    }
}
