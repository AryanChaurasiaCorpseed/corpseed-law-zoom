package com.lawzoom.companyservice.controller.companyController;

import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.companyService.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/company/{userid}")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	
	@GetMapping()
	public ResponseEntity fetchAllCompanyByUser(@PathVariable("userid") long userId){
		return this.companyService.fetchAllCompanyByUserId(userId);
	}

	/*@GetMapping("/info")
	public ResponseEntity fetchAllCompanyInfoByUser(@PathVariable("userid") long userId){
		return this.companyService.fetchAllCompanyInfoByUser(username);
	}*/

	@GetMapping("/{companyId}")
	public ResponseEntity fetchCompanyByUserAndCompanyId(@PathVariable("userid") long userId,@PathVariable("companyId") Long companyId){
		return this.companyService.fetchCompanyByUserIdAndCompanyId(userId,companyId);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity fetchCompanyByUserAndNameOrCIN(@PathVariable("userid") Long userId,@PathVariable("name") String data){
		return this.companyService.fetchCompaniesByUserAndNameOrCIN(userId,data);
	}

	@PostMapping("/save")
	public ResponseEntity saveCompany(@RequestBody CompanyRequest companyRequest,@PathVariable("userid") long userId){
		System.out.println("save company called........."+companyRequest);
		return this.companyService.saveCompany(userId,companyRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateCompany(@RequestBody CompanyRequest companyRequest,@PathVariable("userid") long userId){
		return this.companyService.updateCompany(userId,companyRequest);
	}

	/*@DeleteMapping("/{companyId}")
	public ResponseEntity deleteCompany(@PathVariable("companyId") int companyId,@PathVariable("userid") long userId){
		return this.companyService.deleteCompany(username,companyId);
	}*/

}
