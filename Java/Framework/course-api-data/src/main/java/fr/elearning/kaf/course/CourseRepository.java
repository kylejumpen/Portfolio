package fr.elearning.kaf.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
 
//	public List<Course> findByName(String topicId);
//	public List<Course> findByDescription(String description);
	public List<Course> findByTopicId(String topicId);
}
