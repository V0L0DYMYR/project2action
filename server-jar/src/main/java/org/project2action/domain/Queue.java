package org.project2action.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "QUEUES")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "OWNER_ID")
    private Long ownerId;

    public Queue(String name, Long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    @JsonCreator
    public Queue(@JsonProperty("id") Long id,
                 @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }

    public Queue(Queue queue, User owner){
        this.id = queue.getId();
        this.name = queue.getName();
        this.ownerId = owner.getId();
    }

    public Queue() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

}
