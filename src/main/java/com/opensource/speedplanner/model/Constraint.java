package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "constraints")
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
@Setter
public abstract class Constraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int type;

    //public boolean activeFilter;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_constraint_id", referencedColumnName = "id")
    public Constraint nextConstraint;



}
