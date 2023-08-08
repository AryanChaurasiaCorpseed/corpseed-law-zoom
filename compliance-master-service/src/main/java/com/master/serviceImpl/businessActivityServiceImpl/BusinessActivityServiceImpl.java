package com.master.serviceImpl.businessActivityServiceImpl;

import com.master.dao.businessActivityDao.BusinessActivityDao;
import com.master.dao.subIndustryDao.SubIndustryDao;
import com.master.dto.businessActivityDto.BusinessActivityRequest;
import com.master.dto.businessActivityDto.BusinessActivityResponse;
import com.master.model.businessActivityModel.BusinessActivity;
import com.master.model.subIndustryModel.SubIndustry;
import com.master.response.ResponseEntity;
import com.master.service.businessActivityService.BusinessActivityService;
import com.master.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessActivityServiceImpl implements BusinessActivityService {

	@Autowired
	private BusinessActivityDao businessActivityDao;

	@Autowired
	private SubIndustryDao subIndustryDao;

	@Autowired
	private ResponseMapper responseMapper;

	@Override
	public ResponseEntity fetchAllBusinessActivity() {
		List<BusinessActivity> businessActivityList=this.businessActivityDao.fetchAllBusinessActivity();
		if(businessActivityList.isEmpty())
			return new ResponseEntity().noContent();

		return new ResponseEntity().ok(businessActivityList.stream().map(this::mapToBusinessActivityResponse));
	}

	private BusinessActivityResponse mapToBusinessActivityResponse(BusinessActivity ba) {
		return this.responseMapper.mapToBusinessActivityResponse(ba);
	}

	@Override
	public ResponseEntity saveBusinessActivity(BusinessActivityRequest baRequest) {
		BusinessActivity findBusinessActivity=this.businessActivityDao.
				findBusinessActivityBySubIndustryAndTitle(baRequest.getSubIndustry(),
						baRequest.getTitle());

		if(findBusinessActivity!=null)
			return new ResponseEntity().badRequest("Business Activity already exist !!");

		BusinessActivity saveBusinessActivity=this.businessActivityDao.saveBusinessActivity(this.responseMapper.mapToSaveBusinessActivity(baRequest));

		if(saveBusinessActivity==null)
			return new ResponseEntity().internalServerError();

		return new ResponseEntity().ok();
	}

	@Override
	public ResponseEntity updateBusinessActivity(BusinessActivityRequest baRequest) {
		BusinessActivity findBusinessActivity=this.businessActivityDao.
				findBusinessActivityBySubIndustryAndTitleAndIdNot(baRequest.getSubIndustry(),
						baRequest.getTitle(),baRequest.getId());

		if(findBusinessActivity!=null)
			return new ResponseEntity().badRequest("Business Activity already exist !!");

		BusinessActivity updateBusinessActivity=this.businessActivityDao
				.updateBusinessActivity(this.responseMapper.mapToUpdateBusinessActivity(baRequest));

		if(updateBusinessActivity==null)
			return new ResponseEntity().internalServerError();

		return new ResponseEntity().ok();
	}

	@Override
	public ResponseEntity fetchBusinessActivityById(Long businessActivityId) {
		BusinessActivity businessActivity=this.businessActivityDao.fetchBusinessActivityById(businessActivityId);
		if(businessActivity==null)
			return new ResponseEntity().badRequest("Business Activity Not Found !!");

		return new ResponseEntity().ok(mapToBusinessActivityResponse(businessActivity));
	}

	@Override
	public ResponseEntity deleteBusinessActivityById(Long businessActivityId) {
		BusinessActivity businessActivity=this.businessActivityDao.fetchBusinessActivityById(businessActivityId);
		if(businessActivity==null)
			return new ResponseEntity().badRequest("Business Activity Not Found !!");

		boolean deleteBusiness=this.businessActivityDao.deleteBusinessActivity(businessActivity);

		if(!deleteBusiness)
			return new ResponseEntity().internalServerError();

		return new ResponseEntity().ok();
	}

	@Override
	public ResponseEntity searchBusinessActivity(String searchData) {
		List<BusinessActivity> businessActivityList=this.businessActivityDao.findBusinessActivityContains(searchData);
		if(businessActivityList.isEmpty())
			return new ResponseEntity().noContent();

		return new ResponseEntity().ok(businessActivityList.stream().map(this::mapToBusinessActivityResponse));
	}

	@Override
	public ResponseEntity searchBusinessActivityBySubIndustryId(Long subIndustryId) {
		SubIndustry subIndustry=this.subIndustryDao.fetchSubIndustryById(subIndustryId);
		if(subIndustry==null)
			return new ResponseEntity().badRequest("Sub-Industry Not Found !!");

		List<BusinessActivity> businessActivityList=this.businessActivityDao.findBusinessActivityBySubIndustry(subIndustry);
		if(businessActivityList.isEmpty())
			return new ResponseEntity().noContent();

		return new ResponseEntity().ok(businessActivityList.stream().map(this::mapToBusinessActivityResponse));
	}
}
