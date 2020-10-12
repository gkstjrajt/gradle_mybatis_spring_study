package gradle_mybatis_spring_study.mapper;

import gradle_mybatis_spring_study.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
}
