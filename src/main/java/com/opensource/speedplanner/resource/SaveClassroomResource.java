package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveClassroomResource {
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String classroomName;

    @NotBlank
    @NotNull
    private String type;

    @NotBlank
    @NotNull
    private int capacity;

}
