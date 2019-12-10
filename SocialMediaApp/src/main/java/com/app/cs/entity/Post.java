/**
 * 
 */
package com.app.cs.entity;

import java.util.Date;

/**
 * @author Komal
 *
 */
public class Post implements Comparable<Post> {

	private Integer postId;
	private String contents;
	private String postedBy;
	private Date postedAt;
	/**
	 * @return the postId
	 */
	public Integer getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return postedBy;
	}
	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	/**
	 * @return the postedAt
	 */
	public Date getPostedAt() {
		return postedAt;
	}
	/**
	 * @param postedAt the postedAt to set
	 */
	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}
	/**
	 * @param postId
	 * @param contents
	 * @param postedBy
	 * @param postedAt
	 */
	public Post(Integer postId, String contents, String postedBy, Date postedAt) {
		super();
		this.postId = postId;
		this.contents = contents;
		this.postedBy = postedBy;
		this.postedAt = postedAt;
	}
	@Override
	public int compareTo(Post o) {
		// TODO Auto-generated method stub
		return o.getPostedAt().compareTo(getPostedAt());
	}
	
	
}
