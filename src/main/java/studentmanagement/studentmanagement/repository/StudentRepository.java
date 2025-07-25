package studentmanagement.studentmanagement.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT MAX(id) FROM students")
  Integer getMaxStudentId();

  @Select("SELECT * FROM students_courses")
  @Results({@Result(property = "id", column = "id"),
      @Result(property = "memberId", column = "memberId"),
      @Result(property = "course", column = "course"),
      @Result(property = "courseStartDate", column = "courseStartDate"),
      @Result(property = "courseEndDate", column = "courseEndDate")})
  List<StudentsCourses> searchStudentsCourses();

  @Insert("INSERT INTO students (id, name) VALUES (#{id}, #{name})")
  void insertStudent(Student student);

  @Insert("INSERT INTO students_courses (memberId, course, courseStartDate, courseEndDate) " +
      "VALUES (#{memberId}, #{course}, #{courseStartDate}, #{courseEndDate})")
  void insertStudentsCourses(StudentsCourses sc);
}
