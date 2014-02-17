package org.project2action.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.project2action.common.Utils;

import static org.project2action.common.Utils.initializeIfNull;


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
    private List<Project> projects;
    
    @Transient
    private Long authorId;
    
    @JsonProperty("authorId")
    @JsonInclude(Include.NON_NULL)
    public Long getAuthorId(){
      return (authorId==null) ? (author!=null ? author.getId() : null)
    		                  : authorId ;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_LIKE_IDEAS",
       joinColumns = {@JoinColumn(name = "IDEA_ID")},
       inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    @JsonIgnore
    private Set<User> likers;

    @Transient
    private Integer likes = 0;
    
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

	public Set<User> getLikers() {
		return likers;
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
    
    public Idea() { }

    public Idea(Long id) {
        this.id = id;
    }

    private Idea(Idea prev, User newAuthor)
    {
    	this.id = prev.id;
    	this.name = prev.name;
    	this.description = prev.description;
    	this.likers = prev.likers;
    	this.author = newAuthor;
        this.authorId = newAuthor.getId();
    }
    
    public Idea withAuthor(User newAuthor) {
    	return new Idea(this, newAuthor);
    }
    
    private Idea(Idea prev, Set<User> newLikes) {
        this.id = prev.id;
    	this.name = prev.name;
    	this.description = prev.description;
    	this.likers = initializeIfNull(prev.likers);
        this.likers.addAll(newLikes);
    	this.author = prev.getAuthor();
    }

    public Idea withLikes(Set<User> newLikes) {
    	return new Idea(this, newLikes);
    }
   
    public Idea withProjects(List<Project> projects){
        this.projects = projects;
        return this;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Integer getLikes() {
        return likers != null ? likers.size() : 0;
    }
}
