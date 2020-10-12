package gradle_mybatis_spring_study.mapper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import gradle_mybatis_spring_study.dto.PhoneNumber;
import gradle_mybatis_spring_study.dto.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
	protected static final Log log = LogFactory.getLog(StudentMapperTest.class);

	@After // 결과화면에서 테스트 하나 실행 후 한줄 띄워서 다른 테스트 실행하기 위해서
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private StudentMapper mapper;

	@Test
	public void test05SelectStudentByNo() {
		System.out.println("testSelectStudentByNo");
		Student student = new Student();
		student.setStudId(4);
		Student selectedStd = mapper.selectStudentByNo(student);
		Assert.assertNotNull(selectedStd);
		log.debug(selectedStd.toString());
	}

	@Test
	public void test02SelectStudentByNoWithResultMap() {
		System.out.println("selectStudentByNoWithResultMap");
		Student student = new Student();
		student.setStudId(1);
		Student selectedStd = mapper.selectStudentByNoWithResultMap(student);
		Assert.assertNotNull(selectedStd);
		log.debug(selectedStd.toString());
	}

	@Test
	public void test01SelectStudentByAll() {
		System.out.println("selectStudentByAll");
		List<Student> stdList = mapper.selectStudentByAll();
		Assert.assertNotNull(stdList);
		stdList.stream().forEach(System.out::println);
	}

	@Test
	public void test03InsertStudent() {
		System.out.println("insertStudent");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1998, 1, 16);

		System.out.println("insertStudent");
		Student student = new Student();
		student.setStudId(4);
		student.setName("Doris");
		student.setEmail("Doris@gmail.com");
		student.setPhone(new PhoneNumber("555-666-3333"));
		student.setDob(newDate.getTime());
		int res = mapper.insertStudent(student);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04UpdateStudent() {
		System.out.println("updateStudent");
		Student student = new Student();
		student.setName("Ping");
		student.setEmail("Ping@gmail.com");
		student.setPhone(new PhoneNumber("322-1234-2211"));
		student.setStudId(4);
		int res = mapper.updateStudent(student);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test06DeleteStudent() {
		System.out.println("deleteStudent");
		Student student = new Student();
		student.setStudId(4);
		int res = mapper.deleteStudent(student);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test7SelectStudentByAllForResutlMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Student> list = mapper.selectStudentByAllForResultMap();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test8SelectStudentByAllForHashMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Map<String, Object>> list = mapper.selectStudentByAllForHashMap();
		Assert.assertNotNull(list);
		for (Map<String, Object> map : list) {
			for (Entry<String, Object> e : map.entrySet()) {
				log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
			}
		}
	}
	
	@Test
	public void test9SelectStudentByNoAssociation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student seletedStd = mapper.selectStudentByNoAssociation(student);
		Assert.assertNotNull(seletedStd);
		log.debug(seletedStd.toString());
	}

}
