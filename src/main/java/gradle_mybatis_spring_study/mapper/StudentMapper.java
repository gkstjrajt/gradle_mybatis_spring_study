package gradle_mybatis_spring_study.mapper;

import java.util.List;
import java.util.Map;

import gradle_mybatis_spring_study.dto.Student;

public interface StudentMapper {
	//TypeHandler 이용
	Student selectStudentByNo(Student student);
	
	//ResultMap 이용
	Student selectStudentByNoWithResultMap(Student student);
	
	List<Student> selectStudentByAll();
	
	int insertStudent(Student student);
	
	int updateStudent(Student student);
	
	int deleteStudent(Student student);
	
	//ResultMap
	List<Student> selectStudentByAllForResultMap();
	
	//Result - HashMap
	List<Map<String, Object>> selectStudentByAllForHashMap();
	
	/* 내포된 결과매핑(ResultMap)을 사용한 일대일 매핑 */
	Student selectStudentByNoAssociation(Student student);
	
	/* enum 타입 다루기 */
	int insertEnumStudent(Student student);
}
