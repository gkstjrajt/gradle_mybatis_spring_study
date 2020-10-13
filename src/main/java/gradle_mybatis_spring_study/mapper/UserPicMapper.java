package gradle_mybatis_spring_study.mapper;

import gradle_mybatis_spring_study.dto.UserPic;

public interface UserPicMapper {
	int insertUserPic(UserPic userPic);
	
	UserPic getUserPic(int id);
	
	int deleteUserPic(int id);
}
