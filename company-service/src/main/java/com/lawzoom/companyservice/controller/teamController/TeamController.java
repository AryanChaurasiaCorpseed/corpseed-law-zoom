package com.lawzoom.companyservice.controller.teamController;

import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.teamService.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/company/team/{companyId}")
public class TeamController {

	@Autowired
	private TeamService teamService;


	@GetMapping()
	public ResponseEntity fetchAllTeam(@PathVariable("companyId") Long companyId){
		return this.teamService.fetchAllTeam(companyId);
	}
	
	@PostMapping("/save")
	public ResponseEntity saveTeam(@Valid @RequestBody TeamRequest teamRequest,@PathVariable("companyId") Long companyId){
		return this.teamService.saveTeam(teamRequest,companyId);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateTeam(@Valid @RequestBody TeamRequest teamRequest,@PathVariable("companyId") Long companyId){
		return this.teamService.updateTeam(teamRequest,companyId);
	}
	
	@GetMapping("/{teamId}")
	public ResponseEntity fetchTeamById(@PathVariable("teamId") Long teamId,@PathVariable("companyId") Long companyId){
		return this.teamService.fetchTeamById(teamId,companyId);
	}

	@DeleteMapping("/{teamId}")
	public ResponseEntity deleteTeam(@PathVariable("teamId") Long teamId,@PathVariable("companyId") Long companyId){
		return this.teamService.deleteTeam(teamId,companyId);
	}
}
