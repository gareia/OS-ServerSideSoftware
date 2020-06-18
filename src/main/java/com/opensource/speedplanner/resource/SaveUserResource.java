package com.opensource.speedplanner.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opensource.speedplanner.model.InscriptionProcess;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class SaveUserResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(max = 30)
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inscription_process_id", referencedColumnName = "id")
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;
}
