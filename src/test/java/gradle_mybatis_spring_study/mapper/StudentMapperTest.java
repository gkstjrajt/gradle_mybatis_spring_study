package gradle_mybatis_spring_study.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import gradle_mybatis_spring_study.dto.Gender;
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
	public void test10SelectStudentByAllForResutlMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Student> list = mapper.selectStudentByAllForResultMap();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test07SelectStudentByAllForHashMap() {
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
	public void test08SelectStudentByNoAssociation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student seletedStd = mapper.selectStudentByNoAssociation(student);
		Assert.assertNotNull(seletedStd);
		log.debug(seletedStd.toString());
	}

	@Test
	public void test09InsertEnumStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);
		Student student = new Student();
		student.setStudId(5);
		student.setName("test");
		student.setEmail("test@test.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1234-4555"));
		student.setGender(Gender.FEMALE);

		int res = mapper.insertEnumStudent(student);
		Assert.assertEquals(1, res);

		student.setStudId(6);
		student.setName("gregoric");
		student.setEmail("gregoric@gmail.com");
		student.setDob(newDate.getTime());
		student.setGender(Gender.FEMALE);

		int res1 = mapper.insertEnumStudent(student);
		Assert.assertEquals(1, res1);
	}

	@Test
	public void test11selectStudentByMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("name", "Timothy");
		maps.put("email", "timothy@gmail.com");
		Student student = mapper.selectStudentByMap(maps);
		Assert.assertNotNull(student);
		log.debug(student.toString());

		maps.remove("email");
		student = mapper.selectStudentByMap(maps);
		log.debug(student.toString());

		maps.clear();
		maps.put("email", "timothy@gmail.com");
		student = mapper.selectStudentByMap(maps);
		log.debug(student.toString());
	}

	@Test
	public void test12selectAllStudentByMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("name", "Timothy");
		maps.put("email", "timothy@gmail.com");
		List<Student> list = mapper.selectAllStudentByMap(maps);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);

		maps.remove("email");
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);

		maps.clear();
		maps.put("email", "timothy@gmail.com");
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);

		maps.clear();
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test13UpdateSetStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		student.setPhone(new PhoneNumber("111-1234-1234"));
		student.setDob(new Date());
		
		int res = mapper.updateSetStudent(student);
		Assert.assertEquals(1, res);
		
		student.setPhone(new PhoneNumber("111-1111-1111"));
		student.setDob(new GregorianCalendar(1988, 04, 25).getTime());
		
		res = mapper.updateSetStudent(student);
		Assert.assertEquals(1, res);
		
	}

}
