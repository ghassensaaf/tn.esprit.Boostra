package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Tag;

public interface ITagService {
	
	//public List<Tag> AddTag(List<Tag> tags);
	
	//public void AddTagToPost(long tagId, long postId);
	public void AddTagAffectToPost(List<Tag> tags, long postId);
	
	public List<Object> TrendingTags();
}
