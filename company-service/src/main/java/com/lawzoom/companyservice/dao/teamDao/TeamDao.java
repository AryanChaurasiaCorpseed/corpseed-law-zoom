package com.lawzoom.companyservice.dao.teamDao;

import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamModel.Team;
import org.hibernate.query.Query;

import java.util.List;

public interface TeamDao {

    Team saveTeam(Team team);

//    Team findTeamByTeamNameOrUserAndIdNot(String teamName,User user, int id);

    boolean deleteTeam(Team team);

    Team updateTeam(Team team);

    Team fetchTeamByCompanyAndId(Company company, Long teamId);

    Team fetchTeamById(Long teamId);

    List<Team> findTeamByCompany(Company company);

    Team findTeamByCompanyAndName(Company company, String teamName);

//    boolean isUserIsTeamLeader(User user);
}
