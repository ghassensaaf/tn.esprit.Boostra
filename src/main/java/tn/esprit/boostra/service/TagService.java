package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Post;
import tn.esprit.boostra.entity.Tag;
import tn.esprit.boostra.repository.PostRepository;
import tn.esprit.boostra.repository.TagRepository;



@Service
public class TagService implements ITagService{
	
	@Autowired
	TagRepository tr;
	
	@Autowired
	PostRepository pr;
	
	//@Override
	//public List<Tag> AddTag(List<Tag> tags) {
		// return (List<Tag>) tr.saveAll(tags);

	//}

	//@Override
	//public void AddTagToPost(long tagId, long postId) {
		// TODO Auto-generated method stub
		
		//Tag t = tr.findById(tagId).orElse(null);
		//Post p = pr.findById(postId).orElse(null);
		//p.getTags().add(t);
		//pr.save(p);
	//}
	
	@Override
	public void AddTagAffectToPost(List<Tag> tags, long postId) {
		Post p = pr.findById(postId).orElse(new Post());
		for (Tag tag : tags) {
			Tag t = null;
			t = tr.findTopByTag(tag.getTag());
			if(t==null) {
				p.getTags().add(tag);
				tr.save(tag);				
			}
			else
				p.getTags().add(t);
		}
		pr.save(p);
	}

	@Override
	public List<Object> TrendingTags() {
		// TODO Auto-generated method stub
		
		return tr.TrendingTags();
	}
	
	
	
}
