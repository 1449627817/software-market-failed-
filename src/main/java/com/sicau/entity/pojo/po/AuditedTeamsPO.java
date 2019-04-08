package com.sicau.entity.pojo.po;

import com.sicau.entity.dto.Team;

import java.util.ArrayList;
import java.util.List;


/**
 * 待审核团队所有成员信息
 */
public class AuditedTeamsPO {
	private Team team;
	private MemberPO captain;
	private List<MemberPO> memberPOS = new ArrayList<MemberPO>();
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
	public List<MemberPO> getMemberPOS() {
		return memberPOS;
	}
	public void setMemberPOS(List<MemberPO> memberPOS) {
		this.memberPOS = memberPOS;
	}
		
}
