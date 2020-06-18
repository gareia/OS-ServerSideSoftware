package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="professors")
@Getter
@Setter
public class Professor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
    @Column(unique=true)
    private int code;*/

    @Column(unique=true)
    @NotBlank
    @NotNull
    private Long idNumber;

    @NotBlank
    @NotNull
    @Size(max=35)
    private String names;

    @NotBlank
    @NotNull
    @Size(max=35)
    private String lastNames;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Section section;

     */
}
