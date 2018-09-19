package qa.seanqagroup.learningApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

}
