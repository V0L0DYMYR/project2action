package org.project2action.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Asset (resource) needed or provided by somebody
 * @author rssh
 *
 */
@Entity
@Table(name="ASSETS")
public class Asset {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
	    @Column(name="name")
	    private String name;

	    @Column(name="description")
	    private String description;

	    @Column(name="quantity")
	    private Integer quantity;
	   
	    @Column(name="unit")
	    private String unit;
	    
	    @ManyToOne
	    @JoinColumn(name="needed_project_id")
	    @JsonIgnore
	    private Project neededInProject;
	    
	    @Transient
	    private Long neededInProjectId;
	    
	    @ManyToOne
	    @JoinColumn(name="provided_by_id")
	    @JsonIgnore
	    private User providedByUser;
	
	    @Transient
	    private Long providedByUserId;

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public String getUnit() {
			return unit;
		}

		public Project getNeededInProject() {
			return neededInProject;
		}

		public Long getNeededInProjectId() {
			return neededInProjectId==null ? (neededInProject==null ? null : neededInProject.getId())
					                     : neededInProjectId;
			
		}

		public User getProvidedByUser() {
			return providedByUser;
		}

		public Long getProvidedByUserId() {
			return providedByUserId==null ? (providedByUser==null ? null: providedByUser.getId())
					                      : providedByUserId;
		}
		
		private Asset(Asset prev, Project project)
		{
	      copyFrom(prev);
	      this.neededInProject = project;
		}
		
		public Asset withNeededInProject(Project project)
		{
			return new Asset(this,project);
		}
		
		private Asset(Asset prev, User user)
		{
	      copyFrom(prev);
	      this.providedByUser = user;
		}
	
		public Asset withProvidedByUser(User user)
		{
			return new Asset(this,user);
		}
			
		
		private void copyFrom(Asset a)
		{
	      this.id = a.id;
	      this.name = a.name;		
		  this.description = a.description;
		  this.quantity = a.quantity;
		  this.unit = a.unit;
		  this.neededInProject = a.neededInProject;
		  this.neededInProjectId = a.neededInProjectId;
		  this.providedByUser = a.providedByUser;
		  this.providedByUserId = a.providedByUserId;
		}
		
	    
	    
}
