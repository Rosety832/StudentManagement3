package studentmanagement.studentmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;import studentmanagement.studentmanagement.data.StudentsCourses;

import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.domain.StudentDetail;
import studentmanagement.studentmanagement.repository.StudentRepository;

@Slf4j
@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList(){
    return repository.search();
  }


  public List<StudentsCourses> searchStudentsCoursesList(){
    return repository.searchStudentsCourses();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setMemberId(studentDetail.getStudent().getId());
      studentsCourse.setCourseStartDate(LocalDateTime.now());
      studentsCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourse);
    }
  }

  public StudentDetail findStudentDetailById(String id){
    Student student = repository.findById(id);
    List<StudentsCourses> studentsCourses = repository.findCoursesByStudentId(id);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentsCourses(studentsCourses);

    return detail;
  }

  public String generateNextStudentId() {
    Integer maxId = repository.getMaxStudentId();
    int nextId = (maxId == null) ? 1 : maxId + 1;
    return String.format("%03d",nextId);
  }

  public void registerStudentsCourses(StudentsCourses studentsCourses){
      repository.registerStudentsCourses(studentsCourses);
  }

  @Transactional
  public void updateStudentDetail(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setMemberId(studentDetail.getStudent().getId());
      studentsCourse.setCourseStartDate(LocalDateTime.now());
      studentsCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));
      repository.updateStudentsCourses(studentsCourse);
    }
  }
}

