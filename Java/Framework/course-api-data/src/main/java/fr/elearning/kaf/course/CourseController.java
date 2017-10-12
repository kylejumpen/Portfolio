package fr.elearning.kaf.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.elearning.kaf.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/topics/{topicId}/courses", method = RequestMethod.GET)
	public List<Course> getAllCourses(@PathVariable String topicId){
		return courseService.getAllCourses(topicId);
	}
	
	@RequestMapping(value="/topics/{topicId}/courses/{courseId}", method = RequestMethod.GET)
	public Course getCourse(@PathVariable String courseId){
		return courseService.getCourse(courseId);
	}
	
	@RequestMapping(value="/topics/{topicId}/courses", method = RequestMethod.POST)
	public void addTopic(@RequestBody Course course,@PathVariable String topicId){
		course.setTopic(new Topic(topicId,"",""));
		courseService.addCourse(course);	
	}
	
	@RequestMapping(value="/topics/{topicId}/courses/{courseId}", method = RequestMethod.PUT)
	public void updateTopic(@RequestBody Course course, @PathVariable String topicId){
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course);	
	}
	
	@RequestMapping(value="/topics/{topicId}/courses/{courseId}", method = RequestMethod.DELETE)
	public void deleteTopic(@PathVariable String courseId){
		courseService.deleteCourse(courseId);	
	}
}
