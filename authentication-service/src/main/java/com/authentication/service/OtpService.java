package com.authentication.service;

import com.authentication.model.OTP;
import com.authentication.payload.request.OtpResponse;

public interface OtpService {

    OtpResponse generateOtp(String mobile);

    OTP findOtpByMobileAndOtpCode(String mobile, String otp);
}
