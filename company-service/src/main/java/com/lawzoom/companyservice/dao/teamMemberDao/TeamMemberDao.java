package com.lawzoom.companyservice.dao.teamMemberDao;

import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;

public interface TeamMemberDao {
//   TeamMember findTeamMemberByUser(User user);

    TeamMember findTeamMemberByMemberIdAndTeamAndIdNot(Long memberId,Team team, Long teamMemberId);

    TeamMember saveTeamMember(TeamMember teamMember);

    TeamMember updateTeamMember(TeamMember teamMember);

    TeamMember findTeamMemberByTeamAndMemberId(Team team, Long memberId);

    boolean deleteTeamMember(TeamMember teamMember);

}
