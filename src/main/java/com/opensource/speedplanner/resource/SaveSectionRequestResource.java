package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveSectionRequestResource {
    @NotNull
    @Lob
    private String message;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String studentName;
}
