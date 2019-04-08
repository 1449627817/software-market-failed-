package com.sicau.service.impl;


import com.google.gson.JsonObject;
import com.sicau.dao.PublishMapper;
import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.TeamPO;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import com.sicau.service.PublishService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ttxxi
 * write on 2019-1-31
 * 发布系统服务接口的实现类
 */
@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    PublishMapper publishMapper;

    @Override
    public ResultVO publishTeamBy(String condition ,String value){
        try {
            List<Team> list = new ArrayList();

            switch (condition){
                case "direction":
                    System.out.println("进入了根据方向查团队"+value);
                    if(value.equals("0")){
                        list = publishMapper.selectAllTeam();
                        break;
                    }
                    String[] directionList = value.split(",");
//                    List list = new ArrayList();
                    for (String item: directionList) {
                        //先通过dir在team表中查询到所有团队  对应前端 /teambydirection
                        list.addAll(publishMapper.selectTeamByDirection(item));
                    }
                    break;
                case "state":
                    System.out.println("进入了根据状态查团队"+value);
                    //先通过state在team表中查询到所有团队， 对应前端 /teambystate
                    list = publishMapper.selectTeamByState(value);
                    break;
                case "-1":
                    System.out.println("进入了根据方向查团队"+value);
                    //先通过dir在team表中查询到所有团队，对应前端/team 中teamId=-1的情况
                    list = publishMapper.selectTeamByDirection(value);
                    break;
                case "0":
                    System.out.println("进入了查所有团队"+condition);
                    //先team表中查询到所有团队， 对应前端 /team 中teamId=0的情况
                    list = publishMapper.selectAllTeam();
                    break;
                default:
                    System.out.println("进入了根据id查团队"+condition);
                    //先team表中查询到所有团队， 对应前端 /team 中teamId!=0 & teamId!=-1的情况
                    list.add(publishMapper.selectTeamByTeamId(condition));
                    break;
            }

            //判断是否查找到数据，如果未查找的直接返回
            if(list.isEmpty()){
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"未查找到数据");
            }
            //将查询到的结果转化为JSONArray
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println(jsonArray.toString());

            return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),getUserToTeamList(jsonArray));
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"查询团队和队员失败，请检查数据库前后是否一致...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(-1,e.toString());
        }
    }

    @Override
    public ResultVO publishProject(String projectId){
        try {
            if(projectId.equals("0")) {
                List<Project> list = publishMapper.selectAllProject();
                if(list.isEmpty()){
                    return new ResultVO(0,"成功，未查找到数据");
                }
                JSONArray jsonArray = JSONArray.fromObject(list);
//                for(int i=0;i<jsonArray.size();i++){
//                    jsonArray.getJSONObject(i).put("undertake", publishMapper.selectUndertakeByProjectId(jsonArray.getJSONObject(i).getString("projectId")));
//                }

                return new ResultVO(0,"成功",getUndertakeTeam(jsonArray));
            }else {
                Project project = publishMapper.selectProjectById(projectId);
                if (project == null){
                    return new ResultVO(-1,"未查找到数据");
                }
//                JSONObject jsonObject = JSONObject.fromObject(project);
//                jsonObject.put("undertake", publishMapper.selectUndertakeByProjectId(projectId));//查询对应的正在申请的团队信息
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.add(jsonObject);

                JSONArray jsonArray = new JSONArray();
                jsonArray.add(JSONObject.fromObject(project));


                return new ResultVO(0,"成功",getUndertakeTeam(jsonArray));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(-1,e.toString());
        }
    }


    /**
     *
     * 为teamList获取队长队员信息，返回的是获取完信息的teamList
     * @param jsonArray team的JsonArray
     * @return 获取完信息的teamList
     */
    public JSONArray getUserToTeamList(JSONArray jsonArray){
        //依次取出每一个团队id，再去查抄团队对应的队长、队员
        for(int i = 0 ; i<jsonArray.size();i++){
            String teamId = jsonArray.getJSONObject(i).getString("teamId");
            System.out.println("第"+i+"队teamID:"+teamId);
            //查询团队id对应的队长队员
            List<TeamAndUser> teamAndUserList = publishMapper.selectTeamAndUserById(teamId);
            JSONArray jsonArray1 = JSONArray.fromObject(teamAndUserList);
            //查询队长的信息
            User captain = publishMapper.selectUserById(jsonArray1.getJSONObject(0).getString("captain"));
            System.out.println("当前第"+i+"队队长id为："+captain.getUserId());
            //设置captainInformation
            jsonArray.getJSONObject(i).element("captainInformation", new JSONArray().fromObject(captain));
            //用来储存队员信息的数组
            JSONArray memberArray = new JSONArray();
            for(int j = 0 ; j<jsonArray1.size() ; j++){
                //查询队员的信息
                User member = publishMapper.selectUserById(jsonArray1.getJSONObject(j).getString("member"));
                memberArray.add(member);
            }
            jsonArray.getJSONObject(i).element("memberInformation", memberArray);
            //再去查询对应团队已经完成的作品
            List<String> projectIdList = publishMapper.selectWorksByTeamId(teamId);
            System.out.println("对应团队id"+teamId+"查询到的works集合有:"+projectIdList.toString());
            JSONArray projectArray = new JSONArray();
            for (int t = 0; t<projectIdList.size() ; t++){
                projectArray.add(publishMapper.selectProjectById(projectIdList.get(t)));
            }
            jsonArray.getJSONObject(i).element("works", projectArray);
        }
        return jsonArray;
    }

    @Override
    public ResultVO publishProjectByState(String state){

        List<Project> list = new ArrayList<>();
        String[] stateList = state.split(",");

        for (String item: stateList) {
            list.addAll(publishMapper.selectProjectByState(item));
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        for(int i=0;i<jsonArray.size();i++){
            jsonArray.getJSONObject(i).put("undertake", publishMapper.selectUndertakeByProjectIdState2(jsonArray.getJSONObject(i).getString("projectId")));
            int applyNum = publishMapper.selectUndertakeByProjectIdState1().size();
            jsonArray.getJSONObject(i).put("applyNum", applyNum);
        }
        return new ResultVO(0,"成功",jsonArray);
    }

    /**
     * 传入一个 Project 的jsonArray
     * @param jsonArray
     * @return 让里面的每一个project多一个 undertake字段，对应该项目的承接团队
     */
    JSONArray getUndertakeTeam(JSONArray jsonArray){
        //对每一个project的jsonobject进行操作
        for(int i=0;i<jsonArray.size();i++){

            jsonArray.getJSONObject(i).put("undertake", new ArrayList());
            //正在申请该项目的团队数量
            int applyNum = publishMapper.selectUndertakeByProjectIdState1().size();
            jsonArray.getJSONObject(i).put("applyNum", applyNum);
            List projectList = publishMapper.selectUndertakeByProjectIdState2(jsonArray.getJSONObject(i).getString("projectId"));
            System.out.println("这是第"+i+"个project"+",,projectId为"+jsonArray.getJSONObject(i).getString("projectId"));
            if(projectList.isEmpty()){

                System.out.println(jsonArray.getJSONObject(i).getString("projectId")+" 没  有undertake");
                continue;
            }
            System.out.println(jsonArray.getJSONObject(i).getString("projectId")+"有  undertake");
            //获取对应project的undertake 的jsonArray


            JSONArray undertakeJsonArray = JSONArray.fromObject(projectList);
            //创建一个新的jsonArray用于承接undertake里面teamId对应的team
            JSONArray teamJsonArray = new JSONArray();
            //想teamJsonArray里面添加团队信息
            for(int j=0;j<undertakeJsonArray.size();j++){
                String teamId = undertakeJsonArray.getJSONObject(j).getString("teamId");
                System.out.println(teamId);
                Team team = publishMapper.selectTeamByTeamId(teamId);

                if(team==null){
                    continue;
                }
                teamJsonArray.add(JSONObject.fromObject(team));
            }
            if (!teamJsonArray.isEmpty()){
                jsonArray.getJSONObject(i).put("undertake", teamJsonArray);
            }

        }
        return jsonArray;
    }




}
