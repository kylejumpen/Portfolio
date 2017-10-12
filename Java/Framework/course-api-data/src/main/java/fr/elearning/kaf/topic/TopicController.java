package fr.elearning.kaf.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value="/topics", method = RequestMethod.GET)
	public List<Topic> getAllTopics(){
		return topicService.getAllTopics();
	}
	
	@RequestMapping(value="/topics/{id}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable String id){
		return topicService.getTopic(id);
	}
	
	@RequestMapping(value="/topics", method = RequestMethod.POST)
	public void addTopic(@RequestBody Topic topic){
		topicService.add(topic);	
	}
	
	@RequestMapping(value="/topics/{id}", method = RequestMethod.PUT)
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id){
		topicService.update(topic);	
	}
	
	@RequestMapping(value="/topics/{id}", method = RequestMethod.DELETE)
	public void deleteTopic(@PathVariable String id){
		topicService.delete(id);	
	}
}
