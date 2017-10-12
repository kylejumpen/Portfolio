package fr.elearning.kaf.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	
	public List<Topic> getAllTopics(){

		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}
	
	public Topic getTopic(String id){
		return topicRepository.findOne(id);
	}

	public void add(Topic topic) {
		topicRepository.save(topic);
	}

	public void update(Topic topic) {
		topicRepository.save(topic);
	}

	public void delete(String id) {
		topicRepository.delete(id);
	}

}
