package com.authentication.repository;

import com.authentication.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OTP,Long> {

    Optional<OTP> findByMobileContaining(String mobile);

    OTP findByMobileContainingAndOtpCode(String mobile, String otp);
}
