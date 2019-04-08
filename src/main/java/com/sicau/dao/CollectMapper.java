package com.sicau.dao;


import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.TeamPO;

import java.util.List;

/**
 * @author ttxxi
 * write on 2019-01-31
 * 征集系统的Dao层接口
 */
public interface CollectMapper {

    /**
     * 征集需求对应的向数据库中project表添加数据
     */
    void updateProject(Project nd);

    /**
     * 征集需求对应的向数据库中team添加数据
     */
    void updateTeam(TeamPO tp);

    /**
     * 查询用户id
     * @param ud  包含用户信息的对象
     * @return 用户的id
     */
    String selectUserId(User ud);

    /**
     * 将团队的队长id、队员id、团队id一起上传到teamAndUser表中
     * @param tau teamAndUser表对应实体类对象
     */
    void updateTeamAndUser(TeamAndUser tau);

    /**
     * 将开发团队参与项目征集的信息加入undertake表中
     * @param undertake undertake表的实体类
     */
    void updateUndertake(Undertake undertake);


    /**
     * @Description:获得所有可供选择团队方向
     * @return java.util.List<com.sicau.entity.dto.DirectionName> 列表
     * @author cxh
     * @date 2019/3/7
     **/
    List<DirectionName> selectAllTeamDirection();

    /**
     * 根据学号查询是否有该用户
     * @param studentId
     * @return
     */
    User selectUserByStudentId(String studentId);



}
