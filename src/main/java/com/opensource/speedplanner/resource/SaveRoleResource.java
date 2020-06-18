package com.opensource.speedplanner.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveRoleResource {
    @NotNull
    @NotBlank
    private int type;
}
