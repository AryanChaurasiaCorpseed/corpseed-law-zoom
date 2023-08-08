package com.lawzoom.companyservice.controller.teamMemberController;

import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.teamMemberService.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/company/member/{teamId}")
public class TeamMemberController {

	@Autowired
	private TeamMemberService teamMemberService;

	@GetMapping()
	public ResponseEntity fetchTeamMembers(@PathVariable("teamId") Long teamId){
		return this.teamMemberService.fetchTeamMembers(teamId);
	}
	
	@PostMapping("/save")
	public ResponseEntity saveTeamMember(@Valid @RequestBody TeamMemberRequest tmRequest,
			@PathVariable("teamId") Long teamId){
		return this.teamMemberService.saveTeamMember(tmRequest,teamId);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateTeamMember(@Valid @RequestBody TeamMemberRequest tmRequest,
			@PathVariable("teamId") Long teamId){
		return this.teamMemberService.updateTeamMember(tmRequest,teamId);
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity fetchTeamMember(@PathVariable("memberId") Long memberId,@PathVariable("teamId") Long teamId){
		return this.teamMemberService.fetchTeamMemberByTeamIdAndMemberId(teamId,memberId);
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity deleteTeam(@PathVariable("memberId") Long memberId,@PathVariable("teamId") Long teamId){
		return this.teamMemberService.deleteTeamMember(teamId,memberId);
	}
}
