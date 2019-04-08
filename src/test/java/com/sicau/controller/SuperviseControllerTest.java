package com.sicau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by wzw on 2019/3/10
 *
 * @Author wzw
 */


public class SuperviseControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

   
    public void getAllCategoryTest() throws Exception {
        String responseString = mockMvc.perform
                (
                        MockMvcRequestBuilders.get("/supervise/allTimeNodes")          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .param("id", "1")   //添加参数(可以添加多个)
                        //.param("id","3")   //添加参数(可以添加多个)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

}