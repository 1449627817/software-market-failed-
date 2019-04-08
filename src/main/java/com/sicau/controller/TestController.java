package com.sicau.controller;

import com.sicau.constants.CommonConstants;
import com.sicau.entity.pojo.po.TestTeamPO;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.service.TestService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TestController
 *yesyes
 * @author chenshihang on 2019/1/21
 */
@RestController
public class TestController {

    @Autowired
    private TestService userService;

    @RequestMapping(CommonConstants.PUB_PREFIX + "/test")
    public ResultVO test() {
        ResultVO resultVO = userService.login();
        return resultVO;
    }

    @RequestMapping("/xt_test")
    public ResultVO test2(TestTeamPO testTeamPO) {


        List<Map<String, String>> memberInformation = testTeamPO.getMemberInformation();
        for (int i = 0; i < memberInformation.size(); i++) {
            Map<String, String> stringStringMap = memberInformation.get(i);
            for (String key : stringStringMap.keySet()) {
                System.out.println(key + " : " + stringStringMap.get(key));
            }
        }

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject = JSONObject.fromObject(testTeamPO.getCaptainInformation());
        jsonArray = JSONArray.fromObject(memberInformation);
        System.out.println(jsonArray);
        System.out.println(jsonObject);
        System.out.println(new TestTeamPO());

        return null;
    }

    @RequestMapping("/test")
    public ResultVO test3(HttpServletRequest request) {
        String uid = request.getRemoteUser();
        String cn = "";
        Principal principal = request.getUserPrincipal();
        if (principal != null && principal instanceof AttributePrincipal) {
            AttributePrincipal aPrincipal = (AttributePrincipal) principal;
            //获取用户信息中公开的Attributes部分
            Map<String, Object> map = aPrincipal.getAttributes();
            // 获取姓名,可以根据属性名称获取其他属性
            cn = (String) map.get("cn");
            return new ResultVO(0, "成功", map);
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> listA = new ArrayList<String>();
        listA.add("A");
        listA.add("v");
        List<String> listB = new ArrayList<String>();
        listB.add("B");
        List<String> listFinal = new ArrayList<String>();
        listFinal.addAll(listA);
        listFinal.addAll(listB);
        System.out.println(listFinal.toString());
    }


}
