package com.master.controller.subIndustryController;

import com.master.dto.subIndustryDto.SubIndustryRequest;
import com.master.response.ResponseEntity;
import com.master.service.subIndustryService.SubIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/master/sub-industry")
public class SubIndustryController {

	@Autowired
	private SubIndustryService subIndustryService;
	
	@GetMapping()
	public ResponseEntity fetchAllSubIndustry(){
		return this.subIndustryService.fetchAllSubIndustry();
	}
	
	@PostMapping("/save")
	public ResponseEntity saveSubIndustry(@Valid @RequestBody SubIndustryRequest subIndustryRequest){
		return this.subIndustryService.saveSubIndustry(subIndustryRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateSubIndustry(@Valid @RequestBody SubIndustryRequest subIndustryRequest){
		return this.subIndustryService.updateSubIndustry(subIndustryRequest);
	}

	@GetMapping("/{industryId}")
	public ResponseEntity fetchSubIndustryByIndustry(@PathVariable("industryId") Long industryId){
		return this.subIndustryService.fetchSubIndustryByIndustry(industryId);
	}
	
	@GetMapping("/edit/{subIndustryId}")
	public ResponseEntity fetchSubIndustryById(@PathVariable("subIndustryId") Long subIndustryId){
		return this.subIndustryService.fetchSubIndustryById(subIndustryId);
	}

	@DeleteMapping("/{subIndustryId}")
	public ResponseEntity deleteSubIndustry(@PathVariable("subIndustryId") Long subIndustryId){
		return this.subIndustryService.deleteSubIndustryById(subIndustryId);
	}
}
