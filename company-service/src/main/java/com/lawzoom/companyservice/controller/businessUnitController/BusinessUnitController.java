package com.lawzoom.companyservice.controller.businessUnitController;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.businessUnitService.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company/business-unit/{gstId}")
public class BusinessUnitController {

	@Autowired
	private BusinessUnitService businessUnitService;

	
	@GetMapping()
	public ResponseEntity fetchAllBusinessUnit(@PathVariable("gstId") Long gstId){
		return this.businessUnitService.fetchAllBusinessUnitByGstId(gstId);
	}
	
	@PostMapping("/save")
	public ResponseEntity saveBusinessUnit(@Valid @RequestBody BusinessUnitRequest businessUnitRequest,@PathVariable("gstId") Long gstId){
		return this.businessUnitService.saveBusinessUnit(businessUnitRequest,gstId);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateBusinessUnit(@Valid @RequestBody BusinessUnitRequest businessUnitRequest,
												@PathVariable("gstId") Long gstId){
		return this.businessUnitService.updateBusinessUnit(businessUnitRequest,gstId);
	}
	
	@GetMapping("/{businessUnitId}")
	public ResponseEntity fetchBusinessUnit(@PathVariable("businessUnitId") Long businessUnitId,@PathVariable("gstId") Long gstId){
		return this.businessUnitService.fetchBusinessUnit(businessUnitId,gstId);
	}

	@DeleteMapping("/{businessUnitId}")
	public ResponseEntity deleteBusinessUnit(@PathVariable("businessUnitId") Long businessUnitId
			,@PathVariable("gstId") Long gstId){
		return this.businessUnitService.deleteBusinessUnit(businessUnitId,gstId);
	}
}
