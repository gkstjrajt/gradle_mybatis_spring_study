package gradle_mybatis_spring_study.mapper;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
	protected static final Log log = LogFactory.getLog(StudentMapperTest.class);

	@After			// 결과화면에서 테스트 하나 실행 후 한줄 띄워서 다른 테스트 실행하기 위해서
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Autowired
	private StudentMapper mapper;

	@Test
	public void testSelectStudentByNo() {
		System.out.println("testSelectStudentByNo");
		Student student = new Student();
		student.setStudId(1);
		Student selectedStd = mapper.selectStudentByNo(student);
		Assert.assertNotNull(selectedStd);
		log.debug(selectedStd.toString());
	}
	
	@Test
	public void selectStudentByNoWithResultMap() {
		System.out.println("selectStudentByNoWithResultMap");
		Student student = new Student();
		student.setStudId(1);
		Student selectedStd = mapper.selectStudentByNoWithResultMap(student);
		Assert.assertNotNull(selectedStd);
		log.debug(selectedStd.toString());
	}
	
	@Test
	public void selectStudentByAll() {
		System.out.println("selectStudentByAll");
		List<Student> stdList = mapper.selectStudentByAll();
		Assert.assertNotNull(stdList);
		stdList.stream().forEach(System.out::println);
	}

}
