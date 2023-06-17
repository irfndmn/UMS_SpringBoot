package com.dmn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "name can not be space")
    @Size(min = 2, max = 25, message = "name '${validatedValue} must be between {min} and {max}")
    private String name;


    @NotBlank(message = "lastname can not be space")
    @Size(min = 2, max = 25, message = "lastname '${validatedValue} must be between {min} and {max}")
    private String lastname;

    //@Setter(AccessLevel.NONE)   bir variableda setter ya da getter olmasini istemiyosak
    private Integer grade;

    @Email(message = "please provide valid email")
    private String email;

    private String phoneNumber;
}