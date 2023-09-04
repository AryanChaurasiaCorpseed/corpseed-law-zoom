package com.authentication.serviceImpl;

import com.authentication.model.OTP;
import com.authentication.model.Roles;
import com.authentication.model.User;
import com.authentication.payload.request.SignupRequest;
import com.authentication.payload.request.UserRequest;
import com.authentication.payload.response.MessageResponse;
import com.authentication.payload.response.ResponseEntity;
import com.authentication.repository.RoleRepository;
import com.authentication.repository.UserRepository;
import com.authentication.service.OtpService;
import com.authentication.service.UserService;
import com.authentication.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OtpService otpService;

    @Override
    public ResponseEntity<?> signupUser(SignupRequest signupRequest) {
        OTP otp=this.otpService.findOtpByMobileAndOtpCode(signupRequest.getMobile(),signupRequest.getOtp());

        if(otp==null)
            return new ResponseEntity<String>().badRequest("Enter a valid OTP !!");

        User user = this.userRepository.findByMobile(signupRequest.getMobile()).orElse(null);
        if(user!=null)
            return new ResponseEntity<String>().badRequest("Error : User Already Exist !!");

        this.userRepository.save(mapToSignupUser(signupRequest));

        return new ResponseEntity<MessageResponse>().ok(new MessageResponse("Signup Success"));
    }

    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        OTP otp=this.otpService.findOtpByMobileAndOtpCode(userRequest.getMobile(),userRequest.getOtp());

        if(otp==null)
            return new ResponseEntity<String>().badRequest("Enter a valid OTP !!");


        User findUser = this.userRepository.findByMobileOrEmail(userRequest.getMobile(),userRequest.getEmail()).orElse(null);
        if(findUser!=null)
            return new ResponseEntity<String>().badRequest("Error : User Already Exist !!");

        User saveUser=this.userRepository.save(mapToSaveUser(userRequest));

        return new ResponseEntity<User>().ok(saveUser);

    }

    private User mapToSaveUser(UserRequest u) {
        return User.builder().firstName(u.getFirstName())
                .lastName(u.getLastName()).email(u.getEmail())
                .mobile(u.getMobile()).password(CommonUtil.encodePassword(u.getPassword()))
                .designation(u.getDesignation()).resourceType(u.getResourceType())
                .createdAt(CommonUtil.getDate()).updatedAt(CommonUtil.getDate()).isEnable(true)
                .roles(u.getRoles()).build();
    }

    private User mapToSignupUser(SignupRequest signupRequest) {
        return User.builder().id(0L).mobile(signupRequest.getMobile())
                .password(new BCryptPasswordEncoder().encode(signupRequest.getPassword()))
                .resourceType("In-House").createdAt(new Date()).updatedAt(new Date())
                .isEnable(true).roles(setAdminRole()).build();
    }

    private Set<Roles> setAdminRole() {
        return roleRepository.findByRole("ROLE_ADMIN");
    }



}
