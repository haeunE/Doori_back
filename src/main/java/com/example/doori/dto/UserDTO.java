package com.example.doori.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
    
    // 회원 ID - 빈 값이거나 길이가 50을 초과할 수 없습니다.
    @NotEmpty(message = "아이디는 필수 입력입니다.")
    @Size(min = 5, max = 50, message = "아이디는 5자 이상 50자 이하로 입력해야 합니다.")
    private String username;
    
    // 회원 비밀번호 - 빈 값이거나 길이가 30을 초과할 수 없습니다.
    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하로 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
             message = "비밀번호는 최소 8자 이상, 하나 이상의 문자와 숫자를 포함해야 합니다.")
    private String password;

    // 이름 - 10자 이하로 입력해야 합니다.
    @NotEmpty(message = "이름은 필수 입력입니다.")
    @Size(max = 10, message = "이름은 10자 이하로 입력해야 합니다.")
    private String name;

    // 이메일 - 유효한 이메일 형식이어야 합니다.
    @NotEmpty(message = "이메일은 필수 입력입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    // 전화번호 - 정확한 형식의 전화번호를 요구합니다.
    @NotEmpty(message = "전화번호는 필수 입력입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다. 예: 010-1234-5678")
    private String tel;
}
