package org.project2action.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="IDEAS")
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
    
    //documents ???
    
    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnore
    private User author;
    
    @Transient
    private Long authorId;
    
    @JsonProperty("authorId")
    @JsonInclude(Include.NON_NULL)
    public Long getAuthorId(){
      return (authorId==null) ? (author!=null ? author.getId() : null)
    		                  : authorId ;
    }
    
    @ManyToMany
    @JoinTable(name = "USERS_LIKE_IDEAS",
       joinColumns = {@JoinColumn(name = "IDEA_ID")},
       inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    @JsonIgnore
    private Set<User> likes;
    
	public Long getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public User getAuthor() {
		return author;
	}

	public Set<User> getLikes() {
		return likes;
	}
     
    @JsonCreator
    public Idea(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("description")String description
                ){
         this.id = id;
         this.name = name;
         this.description = description;
    }
    
    public Idea()
    {
     // make hibernate happy	
    }

    private Idea(Idea prev, User newAuthor)
    {
    	this.id = prev.id;
    	this.name = prev.name;
    	this.description = prev.description;
    	this.likes = prev.likes;
    	this.author = newAuthor;
        this.authorId = newAuthor.getId();
    }
    
    public Idea withAuthor(User newAuthor) {
    	return new Idea(this, newAuthor);
    }
    
    private Idea(Idea prev, Set<User> newLikes)
    {
    	this.id = prev.id;
    	this.name = prev.name;
    	this.description = prev.description;
    	this.likes = newLikes;
    	this.author = prev.getAuthor();
    }

    public Idea withLikes(Set<User> newLikes) {
    	return new Idea(this, newLikes);
    }
   
    
}
