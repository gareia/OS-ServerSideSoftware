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

    @Column(name = "codes", unique=true)
    private int code;

    @Column(name = "id_numbers", unique=true)
    @NotBlank
    @NotNull
    private Long idNumber;

    @Column(name = "names")
    @NotBlank
    @NotNull
    @Size(max=35)
    private String names;

    @Column(name = "last_names")
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
