package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClassroomService {
    Page<Classroom> getAllClassroom(Pageable pageable);
    Page<Classroom> getAllClassroomByCourseId(Long courseId, Pageable pageable);
    Classroom assignClassroomCourse(Long classroomId, Long courseId);
    Classroom unassignClassroomCourse(Long classroomId, Long courseId);
    Classroom getClassroomById(Long classroomId);
    Classroom createClassroom(Classroom classroom);
    Classroom updateClassroom(Long classroomId, Classroom classroomDetails);
    ResponseEntity<?> deleteClassroom(Long classroomId);
}
