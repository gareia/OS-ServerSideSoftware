package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class SaveUserResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(max = 15)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String email;
/*
    @NotNull
    @NotBlank
    private Long profile_id;*/
}
