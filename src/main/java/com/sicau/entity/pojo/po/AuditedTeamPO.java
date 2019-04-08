package com.sicau.entity.pojo.po;

import com.sicau.entity.dto.Team;

/**
 * 待审核团队单个成员
 */
public class AuditedTeamPO {
	private Team team;
	private MemberPO captain;
	private MemberPO memberPO;
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public MemberPO getCaptain() {
		return captain;
	}
	public void setCaptain(MemberPO captain) {
		this.captain = captain;
	}
	public MemberPO getMemberPO() {
		return memberPO;
	}
	public void setMemberPO(MemberPO memberPO) {
		this.memberPO = memberPO;
	}
	
		
}
