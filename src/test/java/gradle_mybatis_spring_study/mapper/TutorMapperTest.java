package gradle_mybatis_spring_study.mapper;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Course;
import gradle_mybatis_spring_study.dto.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
public class TutorMapperTest {
	protected static final Log log = LogFactory.getLog(StudentMapperTest.class);

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Autowired
	private TutorMapper mapper;

	@Test
	public void testSelectTutorByTutorId() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor findTutor = new Tutor();
		findTutor.setTutorId(1);
		Tutor tutor = mapper.selectTutorByTutorId(findTutor);
		Assert.assertEquals(tutor.getTutorId(), findTutor.getTutorId());
		
		log.trace(tutor.getTutorId() + " : " + tutor.getName());
		List<Course> list = tutor.getCourses();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
