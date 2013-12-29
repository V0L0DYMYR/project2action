package org.project2action.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PROJECTS")
public class Project {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="idea_id")
    @JsonIgnore
    private Idea idea;

    @Transient
    private Long ideaId;
   
    
    @ManyToOne
    @JoinColumn(name="initiator_id")
    @JsonIgnore
    private User initiator;
    
    @Transient
    private Long initiatorId;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "project_participants",
       joinColumns = {@JoinColumn(name = "project_id")},
       inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @JsonIgnore
    private Set<User> participants;
  
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,
    		    mappedBy="neededInProject")
    @JsonIgnore
    private Set<Asset> assets;
     
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    
    @Column(name="resolution")
    private String resolution;
	
    @Column(name="start_date")
    //@Temporal(TemporalType.TIMESTAMP)
    private long   startDate;

    public Project()
    {}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Idea getIdea() {
		return idea;
	}

	public Long getIdeaId() {
		return (ideaId==null) ? (idea==null ? null : idea.getId())
				                   :  ideaId ;
	}
	
	
	public User getInitiator() {
		return initiator;
	}

	public Long getInitiatorId() {
		return (initiatorId==null) ? (initiator==null ? null : initiator.getId())
				                   :  initiatorId ;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public String getResolution() {
		return resolution;
	}

	public Date getStartDate() {
		return new Date(startDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate.getTime();
	}
    
	private Project(Project prev, User newInitiator)
	{
	 copyFrom(prev);
	 this.initiator = newInitiator;
	 this.initiatorId = null;
	}
	
	public Project withInitiator(User initiator)
	{
     return new Project(this, initiator);		
	}

	private Project(Project prev, Date startDate)
	{
	 copyFrom(prev);
	 this.startDate = startDate.getTime();
	}

	public Project withStartDate(Date startDate)
	{
     return new Project(this, startDate);		
	}
	
	
	private void copyFrom(Project p)
	{
      this.id = p.id;
      this.name = p.name;		
	  this.description = p.description;
	  this.idea = p.idea;
	  this.ideaId = p.ideaId;
	  this.initiator = p.initiator;
	  this.initiatorId = p.initiatorId;
	  this.participants = p.participants;
	  this.status = p.status;
	  this.resolution = p.resolution;
	  this.startDate = p.startDate;
	}
    
}
