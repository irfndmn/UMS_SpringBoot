package com.dmn.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "name can not be space")
    @Size(min = 2, max = 25, message = "name '${validatedValue} must be between {min} and {max}")
    @Column(nullable = false, length = 25)
    private String name;


    @NotBlank(message = "lastname can not be space")
    @Size(min = 2, max = 25, message = "lastname '${validatedValue} must be between {min} and {max}")
    @Column(nullable = false, length = 25)
    private String lastname;

    //@Setter(AccessLevel.NONE)   bir variableda setter ya da getter olmasini istemiyosak
    private Integer grade;


    @Column(nullable = false, unique = true, length = 50)
    @Email(message = "please provide valid email")
    private String email;

    private LocalDateTime createDate = LocalDateTime.now();

    private String phoneNumber;


}
