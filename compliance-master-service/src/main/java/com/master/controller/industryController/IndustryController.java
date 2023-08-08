package com.master.controller.industryController;

import com.master.dto.industryDto.IndustryRequest;
import com.master.response.ResponseEntity;
import com.master.service.industryService.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/master/industry")
public class IndustryController {

	@Autowired
	private IndustryService industryService;
	
	@GetMapping()
	public ResponseEntity allIndustry(){
		return this.industryService.fetchAllIndustries();
	}
	
	@PostMapping("/save")
	public ResponseEntity saveIndustry(@Valid @RequestBody IndustryRequest industryRequest){
		return this.industryService.saveIndustry(industryRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateIndustry(@Valid @RequestBody IndustryRequest industryRequest){
		return this.industryService.updateIndustry(industryRequest);
	}
	
	@GetMapping("/{industryId}")
	public ResponseEntity fetchIndustry(@PathVariable("industryId") Long industryId){
		return this.industryService.fetchIndustryById(industryId);
	}

	@DeleteMapping("/{industryId}")
	public ResponseEntity deleteIndustry(@PathVariable("industryId") Long industryId){
		return this.industryService.deleteIndustryById(industryId);
	}
}
