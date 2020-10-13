package gradle_mybatis_spring_study.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradle_mybatis_spring_study.dto.Student;
import gradle_mybatis_spring_study.handlers.StudentResultHandler;

@Service
public class StudentService {
	private final String namespace = "gradle_mybatis_spring_study.mapper.StudentMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	/* ResultSet 처리방식의 재정의 */
	public Map<Integer, String> selectStudentForMap(int studId) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		StudentResultHandler resultHandler = new StudentResultHandler(map);
		
		sqlSession.select(namespace + ".selectStudentForMap", studId, resultHandler);
		return map;
	}
	
	public Map<Integer, Student> selectStudentForMap2(int studId) {
		Map<Integer, Student> map = new HashMap<Integer, Student>();
		ResultHandler<Student> resultHandler = new ResultHandler<Student>() {

			@Override
			public void handleResult(ResultContext<? extends Student> resultContext) {
				Student student = resultContext.getResultObject();
				map.put(student.getStudId(), student);
			}
		};
		
		sqlSession.select(namespace + ".selectStudentForMap", studId, resultHandler);
		return map;
	}
}
