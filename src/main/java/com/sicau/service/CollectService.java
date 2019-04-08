package com.sicau.service;


import com.sicau.entity.dto.Project;
import com.sicau.entity.dto.Undertake;
import com.sicau.entity.dto.User;
import com.sicau.entity.pojo.po.TeamPO;
import com.sicau.entity.pojo.vo.ResultVO;

/**
 * @author ttxxi
 * write on 2019-1-31
 * 征集系统服务的接口
 */
public interface CollectService {
    /**
     * 项目征集
     * @return 状态信息
     */
    ResultVO collectUndertake(Undertake undertake);

    /**
     * 需求征集
     * @return 状态信息
     */
    ResultVO collectNeed(Project nd);

    /**
     * 开发团队收集
     * @return 状态信息
     */
    ResultVO collectTeam(TeamPO tp);

    /**
     * @Describe 开发团队参与项目征集
     * @author yj
     * @return ResultVO
     */
    ResultVO projectTeamSolicitation(Undertake undertake);


    /**
     * @Description:获得所有可供选择团队方向
     * @return com.sicau.entity.pojo.vo.ResultVO 列表
     * @author cxh
     * @date 2019/3/7
     **/
    ResultVO selectAllTeamDirection();

    ResultVO selectAllUserDirection();

    ResultVO add(User user);

    /**
     * 判断是否为新用户
     * @author ttxxi
     * @param studentId
     * @return
     */
    ResultVO ifNew(String studentId);
}
