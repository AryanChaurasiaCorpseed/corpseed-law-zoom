package com.lawzoom.companyservice.serviceImpl.teamMemberServiceImpl;

import com.lawzoom.companyservice.dao.teamDao.TeamDao;
import com.lawzoom.companyservice.dao.teamMemberDao.TeamMemberDao;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.teamMemberService.TeamMemberService;
import com.lawzoom.companyservice.utility.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ResponseMapper responseMapper;


    @Override
    public ResponseEntity fetchTeamMembers(Long teamId) {
        Team team=teamDao.fetchTeamById(teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");
        return new ResponseEntity().ok(team.getTeamMembers().stream().map(this::mapToTeamMemberResponse));
    }

    private TeamMemberResponse mapToTeamMemberResponse(TeamMember teamMember) {
        return this.responseMapper.mapToTeamMemberResponse(teamMember);
    }

    @Override
    public ResponseEntity saveTeamMember(TeamMemberRequest tmRequest,Long teamId) {
        Team team=teamDao.fetchTeamById(teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        TeamMember findTeamMember=this.teamMemberDao.
                findTeamMemberByTeamAndMemberId(team,tmRequest.getMemberId());
        if(findTeamMember!=null)
            return new ResponseEntity().badRequest("Already Member of this Team !!");

        TeamMember saveTeamMember=this.teamMemberDao.saveTeamMember(
                this.responseMapper.mapToSaveTeamMember(tmRequest,team));

        if(saveTeamMember==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity updateTeamMember(TeamMemberRequest tmRequest,Long teamId) {
        Team team=teamDao.fetchTeamById(teamId);
       if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        TeamMember findTeamMember=this.teamMemberDao.findTeamMemberByMemberIdAndTeamAndIdNot(
                tmRequest.getMemberId(),team,tmRequest.getId());
        if(findTeamMember!=null)
                return new ResponseEntity().badRequest("Already Member of this Team !!");

        TeamMember updateTeamMember=this.teamMemberDao.updateTeamMember(
                this.responseMapper.mapToUpdateTeamMember(tmRequest,team));

        if(updateTeamMember==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity fetchTeamMemberByTeamIdAndMemberId(Long teamId, Long memberId) {
        Team team=teamDao.fetchTeamById(teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        TeamMember teamMember=this.teamMemberDao.findTeamMemberByTeamAndMemberId(team,memberId);
        if(teamMember==null)
            return new ResponseEntity().badRequest("Member Not Found !!");

        return new ResponseEntity().ok(mapToTeamMemberResponse(teamMember));
    }

    @Override
    public ResponseEntity deleteTeamMember(Long teamId, Long memberId) {
        Team team=teamDao.fetchTeamById(teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        TeamMember teamMember=this.teamMemberDao.findTeamMemberByTeamAndMemberId(team,memberId);
        if(teamMember==null)
            return new ResponseEntity().badRequest("Member Not Found !!");

        boolean deleteMember=this.teamMemberDao.deleteTeamMember(teamMember);
        if(!deleteMember)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }
}
