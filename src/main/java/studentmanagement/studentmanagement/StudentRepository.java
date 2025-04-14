package studentmanagement.studentmanagement;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "member_id", column = "member_id"),
      @Result(property = "course", column = "course"),
      @Result(property = "course_startdate", column = "course_startdate"),
      @Result(property = "course_enddate", column = "course_enddate")
  })
  List<StudentsCourses> searchStudentsCourses();
}
