package com.lawzoom.companyservice.controller.gstController;

import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.gstService.GstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/company/gst/{companyId}")
public class GstController {

	@Autowired
	private GstService gstService;

	@GetMapping()
	public ResponseEntity fetchAllGst(@PathVariable("companyId") Long companyId){
		return this.gstService.fetchAllGst(companyId);
	}
	
	@PostMapping("/save")
	public ResponseEntity saveGst(@Valid @RequestBody GstRequest gstRequest,@PathVariable("companyId") Long companyId){
		return this.gstService.saveGst(gstRequest,companyId);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateGst(@Valid @RequestBody GstRequest gstRequest,@PathVariable("companyId") Long companyId){
		return this.gstService.updateGst(gstRequest,companyId);
	}
	
	@GetMapping("/{gstId}")
	public ResponseEntity fetchGstById(@PathVariable("gstId") Long gstId,@PathVariable("companyId") Long companyId){
		return this.gstService.fetchGstByGstIdAndCompanyId(gstId,companyId);
	}

	@GetMapping("/find/{gstNumber}")
	public ResponseEntity findGstByNumber(@PathVariable("gstNumber") String gstNumber,@PathVariable("companyId") Long companyId){
		return this.gstService.findGstByNumberAndCompanyId(gstNumber,companyId);
	}

	@DeleteMapping("/{gstId}")
	public ResponseEntity deleteGST(@PathVariable("gstId") Long gstId,
			@PathVariable("companyId") Long companyId){
		return this.gstService.deleteGstByIdAndCompanyId(gstId,companyId);
	}
}
