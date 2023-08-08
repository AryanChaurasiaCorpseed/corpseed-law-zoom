package com.master.controller.businessActivityController;

import com.master.dto.businessActivityDto.BusinessActivityRequest;
import com.master.response.ResponseEntity;
import com.master.service.businessActivityService.BusinessActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/master/business-activity")
public class BusinessActivityController {
		
	@Autowired
	private BusinessActivityService businessActivityService;

	@GetMapping()
	public ResponseEntity fetchAllBusinessActivity(){
		return this.businessActivityService.fetchAllBusinessActivity();
	}
	
	@PostMapping("/save")
	public ResponseEntity saveBusinessActivity(@Valid @RequestBody BusinessActivityRequest baRequest){
		return this.businessActivityService.saveBusinessActivity(baRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateBusinessActivity(@Valid @RequestBody BusinessActivityRequest baRequest){
		return this.businessActivityService.updateBusinessActivity(baRequest);
	}
	
	@GetMapping("/{businessActivityId}")
	public ResponseEntity fetchBusinessActivityById(@PathVariable("businessActivityId") Long businessActivityId){
		return this.businessActivityService.fetchBusinessActivityById(businessActivityId);
	}

	@DeleteMapping("/{businessActivityId}")
	public ResponseEntity deleteBusinessActivity(@PathVariable("id") Long businessActivityId){
		return this.businessActivityService.deleteBusinessActivityById(businessActivityId);
	}
	
	@GetMapping("/search/{searchData}")
	public ResponseEntity searchBusinessActivity(@PathVariable("searchData") String searchData){

		return this.businessActivityService.searchBusinessActivity(searchData);
	}
	
	@GetMapping("/list/{subIndustryId}")
	public ResponseEntity searchBusinessActivityBySubIndustry(@PathVariable("subIndustryId") Long subIndustryId){
		return this.businessActivityService.searchBusinessActivityBySubIndustryId(subIndustryId);
	}
}
