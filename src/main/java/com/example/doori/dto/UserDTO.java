package com.example.doori.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

    @NotNull(message = "아이디를 입력하세요.")
    @Pattern(regexp = "^[A-Za-z0-9]{5,50}$", message = "아이디는 5자 이상 50자 이하로 영문 또는 숫자만 가능합니다.")
    private String username;

    @NotNull(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$", message = "비밀번호는 8~30자, 문자와 숫자를 포함해야 합니다.")
    private String password;

    @NotNull(message = "이름을 입력하세요.")
    @Pattern(regexp = "^[A-Za-z가-힣]{1,10}$", message = "이름은 한글 또는 영문, 10자 이하로 입력하세요.")
    private String name;

    @NotNull(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "^\\d{11}$", message = "전화번호는 11자리 숫자만 가능합니다.")
    private String tel;

    @NotNull(message = "이메일을 입력하세요.")
    @Email(message = "유효한 이메일 형식을 입력하세요.")
    private String email;

}
