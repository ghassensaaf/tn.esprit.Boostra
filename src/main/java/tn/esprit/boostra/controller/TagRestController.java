package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Tag;
import tn.esprit.boostra.service.IPostService;
import tn.esprit.boostra.service.ITagService;



@RestController
public class TagRestController {

	@Autowired
	ITagService ts;
	
	@Autowired
	IPostService ps;
	
//	@PostMapping("/tag/AddTag")
//	public List<Tag> AddTag(@RequestBody List<Tag> tags) {
//		return  ts.AddTag(tags);
//
//	}
//	@PostMapping("/tag/AddTagToPost")
//	
//	public void AddTagToPost( @RequestParam ("tagId") long tagId, @RequestParam ("postId") long postId) {
//		ts.AddTagToPost(tagId, postId);
//		
//	}
	
	@PostMapping("/tag/AddTagToPost")
	public void AddTagAffectToPost(@RequestBody List<Tag> tags, @RequestParam long postId) {
		
		ts.AddTagAffectToPost(tags, postId);
	}
	
	
	@GetMapping("/tag/trendingtags")
	public List<Object> TrendingTags() {
		return ts.TrendingTags();
	}
	
	
}
