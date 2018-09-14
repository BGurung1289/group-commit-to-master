package qa.seanqagroup.learningApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.UserTakesCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserTakesCourse, Long> {
	List<UserTakesCourse> findByUserId(Long userId);
}
