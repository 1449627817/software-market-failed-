package com.sicau.dao;

import com.sicau.entity.dto.AchievementsAndType;
import com.sicau.entity.dto.AchievementsType;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * @author tzw
 * CreateTime 13:43 2019/2/11
 **/
public interface AchievementsAndTypeMapper {

    String selectByName(@Param("achievementType") String achievementType);

    int insertType(AchievementsType achievementsType);

    int insertAchievementAndType(AchievementsAndType achievementsAndType);
}
