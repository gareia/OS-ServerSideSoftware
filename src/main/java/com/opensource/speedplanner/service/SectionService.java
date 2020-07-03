package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SectionService {

    Section createSection(Long courseId, Section section);
    Page<Section> getAllSectionsByCourseId(Long courseId, Pageable pageable);

}
