package com.lawzoom.companyservice.service.teamMemberService;

import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.response.ResponseEntity;

public interface TeamMemberService {

    ResponseEntity fetchTeamMembers(Long teamId);

    ResponseEntity saveTeamMember(TeamMemberRequest tmRequest,Long teamId);

    ResponseEntity updateTeamMember(TeamMemberRequest tmRequest,Long teamId);

    ResponseEntity fetchTeamMemberByTeamIdAndMemberId(Long teamId, Long memberId);

    ResponseEntity deleteTeamMember(Long teamId, Long memberId);
}
