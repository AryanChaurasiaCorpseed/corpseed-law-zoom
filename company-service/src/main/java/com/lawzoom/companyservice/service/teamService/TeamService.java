package com.lawzoom.companyservice.service.teamService;

import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.response.ResponseEntity;

public interface TeamService {
    ResponseEntity fetchAllTeam(Long companyId);

    ResponseEntity saveTeam(TeamRequest teamRequest, Long companyId);

    ResponseEntity updateTeam(TeamRequest teamRequest, Long companyId);

    ResponseEntity fetchTeamById(Long teamId, Long companyId);

    ResponseEntity deleteTeam(Long teamId, Long companyId);
}
