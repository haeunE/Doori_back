package com.example.doori.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTOPW implements Serializable {

    // 새로 추가된 필드들
    @NotNull(message = "현재 비밀번호를 입력하세요.")
    private String currentPassword;  // 현재 비밀번호

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$", message = "새 비밀번호는 8~30자, 문자와 숫자를 포함해야 합니다.")
    private String newPassword;  // 새 비밀번호 (선택사항)
}
