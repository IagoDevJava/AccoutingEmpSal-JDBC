package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Department {
    Integer id;
    @NotBlank
    String name;
    String address;
    @Pattern(regexp = "^[1-9]-\\d\\d\\d$")
    String phone;
    @Email
    @NotBlank
    String email;
    Integer headId;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public void setAddress(String address) {
        if (address != null) this.address = address;
    }

    public void setPhone(String phone) {
        if (phone != null) this.phone = phone;
    }

    public void setEmail(String email) {
        if (email != null) this.email = email;
    }

    public void setHeadId(Integer headId) {
        if (headId != null) this.headId = headId;
    }
}
