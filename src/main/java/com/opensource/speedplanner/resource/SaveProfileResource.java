package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveProfileResource {
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastNames;

    @NotNull
    @NotBlank
    private boolean gender;

    @NotNull
    @NotBlank
    private int semester;

    @NotNull
    @NotBlank
    private Long idNumber;
}
