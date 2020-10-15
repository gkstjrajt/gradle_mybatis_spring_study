package gradle_mybatis_spring_study.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import gradle_mybatis_spring_study.dto.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseMapperTest {
	protected static final Log log = LogFactory.getLog(CourseMapperTest.class);

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Autowired
	private CourseMapper mapper;

	@Test
	public void test01SelectCourseByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 1);
		
		List<Course> list = mapper.selectCourseByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test02SelectCourseByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseName", "%Java%");
		List<Course> list = mapper.selectCourseByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test03SelectCourseByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", cal.getTime());
		List<Course> list = mapper.selectCourseByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test04SelectCaseCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchBy", "Tutor");
		map.put("tutorId", 1);
		List<Course> list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.replace("searchBy", "CourseName");
		map.remove("tutorId");
		map.put("courseName", "%Java%");
		list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test05SelectWhereCourses() {
		// map에 key값을 주지 않아서 CourseMapper.xml where문에서 key가 없으니 where 없이 바로 Select All 이 되었음
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Map<String, Object>	 map = new HashMap<String, Object>();
		List<Course> list = mapper.selectWhereCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		// map에 key값 tutorId 하나만 주어서 where 뒤에 tutorId만 옴, And는 자동 삭제
		map.put("tutorId", 1);
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);

		// map에 이미 있는 키값 tutorId 이후에 courseName 을 넣어줌 . where tutorId AND courseName 으로 이어짐!
		map.put("courseName", "%Java%");
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		
		// map.clear를 해줘서 위에 넣어준 키값들 제거, 새로 endDate 키값 넣어줌
		map.clear();
		map.put("endDate", new Date());
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		
	}
	
	@Test
	public void test06SelectTrimCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.put("tutorId", 1);
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.clear();
		map.put("courseName", "%Java%");
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.put("tutorId", 1);
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test07SelectCoursesForeachByTutors() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		List<Integer> tutorIds = new ArrayList<Integer>();
		tutorIds.add(1);
		tutorIds.add(2);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorIds", tutorIds);
		
		List<Course> list = mapper.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test08InsertCourses() {
		List<Course> tutors = new ArrayList<Course>();
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		tutors.add(new Course(4, "wowgood", "database", cal.getTime(), cal.getTime(), 3));
		tutors.add(new Course(5, "wowgood", "database", cal.getTime(), cal.getTime(), 3));
		tutors.add(new Course(6, "WindWind", "database", cal.getTime(), cal.getTime(), 4));
		tutors.add(new Course(7, "expensive", "veryvery", cal.getTime(), cal.getTime(), 4));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutors", tutors);
		
		int res = mapper.insertCourses(map);
		Assert.assertEquals(4, res);
	}
	
	@Test
	public void test09UpdateCourses() {
		Course uc = new Course();
		uc.setCourseId(7);
		uc.setDescription("공짜 데이터베이스");
		uc.setName("꽁짜디비");
		int res = mapper.updateCourses(uc);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test10DeleteCourses() {
		List<Integer> courseIds = Arrays.asList(4, 5, 6, 7);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseIds", courseIds);
		
		int res = mapper.deleteCourses(map);
		Assert.assertEquals(4, res);
	}
}











