package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/*Page<Role> findByUserId(Long userId , Pageable pageable);
    Optional<Role> findByIdAndUserId(Long userId , Long roleId);*/
@Repository
public interface SectionRepository extends JpaRepository<Section , Long> {
   Page<Section> findByCourseId(Long courseId , Pageable pageable);
   Optional<Section> findByIdAndCourseId(Long courseId  , Long sectionId);
}
