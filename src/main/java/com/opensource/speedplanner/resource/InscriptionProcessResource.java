package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionProcessResource {
    private Long id;
    private Long periodId;
    private Long userId;
    private int totalCredits;
}
