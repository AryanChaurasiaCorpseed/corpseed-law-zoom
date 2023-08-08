package com.master.controller.stateController;

import com.master.response.ResponseEntity;
import com.master.service.stateService.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/master/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/{countryId}")
    public ResponseEntity fetchStateList(@PathVariable("countryId") Long countryId){
        return this.stateService.fetchAllStates(countryId);
    }
    
    @GetMapping()
    public ResponseEntity fetchAllStateList(){
        return this.stateService.fetchAllStateList();
    }
}
