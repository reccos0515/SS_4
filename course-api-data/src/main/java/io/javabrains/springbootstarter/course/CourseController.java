package io.javabrains.springbootstarter.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(method=RequestMethod.GET, value ="/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id) {
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/topics/{topicId}/courses/{id}") //can use("id") to denote other var name to arg vars
	public Course getCourse(@PathVariable String id) {
		
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course,@PathVariable String topicId) {
		course.setTopic(new Topic(topicId, "",""));
		courseService.addCourse(course);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value = "value = /topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Course course,@PathVariable String topicId,@PathVariable String id) {
		course.setTopic(new Topic(topicId, "",""));
		courseService.updateCourse(course);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		
		courseService.deleteCourse(id);
	}
}
