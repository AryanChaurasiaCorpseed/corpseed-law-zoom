package com.lawzoom.companyservice.serviceImpl.teamServiceImpl;

import com.lawzoom.companyservice.dao.companyDao.CompanyDao;
import com.lawzoom.companyservice.dao.teamDao.TeamDao;
import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.teamService.TeamService;
import com.lawzoom.companyservice.utility.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public ResponseEntity fetchAllTeam(Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company not Found !!");

        return new ResponseEntity().ok(teamDao.findTeamByCompany(company).stream().map(this::mapToTeamResponse));
    }

    private TeamResponse mapToTeamResponse(Team team) {
        return this.responseMapper.mapToTeamResponse(team);
    }

    @Override
    public ResponseEntity saveTeam(TeamRequest teamRequest, Long companyId) {

        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company not Found !!");

        Team findTeam=this.teamDao.findTeamByCompanyAndName(company,teamRequest.getTeamName());
        if(findTeam!=null)
            return new ResponseEntity().badRequest("Already Team Exist !!");

        Team saveTeam=this.teamDao.saveTeam(this.responseMapper.mapToSaveTeam(teamRequest,company));

        if(saveTeam==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity updateTeam(TeamRequest teamRequest, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company not Found !!");

        Team findTeam=this.teamDao.fetchTeamByCompanyAndId(company,teamRequest.getId());
        if(findTeam==null)
            return new ResponseEntity().badRequest("Team not found !!");

        Team updateTeam=this.teamDao.updateTeam(this.responseMapper.mapToUpdateTeam(teamRequest,findTeam));

        if(updateTeam==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity fetchTeamById(Long teamId, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company not Found !!");

        Team team=this.teamDao.fetchTeamByCompanyAndId(company,teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        return new ResponseEntity().ok(mapToTeamResponse(team));
    }

    @Override
    public ResponseEntity deleteTeam(Long teamId, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company not Found !!");

        Team team=this.teamDao.fetchTeamByCompanyAndId(company,teamId);
        if(team==null)
            return new ResponseEntity().badRequest("Team Not Found !!");

        boolean deleteTeam=this.teamDao.deleteTeam(team);
        if(!deleteTeam)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }
}
