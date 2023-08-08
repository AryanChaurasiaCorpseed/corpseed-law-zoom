package com.authentication.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {

    private String mobile;
    private String otp;
    private String password;

}
