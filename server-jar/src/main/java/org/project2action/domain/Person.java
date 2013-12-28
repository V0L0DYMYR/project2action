package org.project2action.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "PEOPLE")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUEUE_ID")
    private Long queueId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @JsonCreator
    public Person(@JsonProperty("ID") Long id,
                  @JsonProperty("FULL_NAME") String fullName,
                  @JsonProperty("USER_ID") Long userId,
                  @JsonProperty("QUEUE_ID") Long queueId){
        this.id = id;
        this.queueId = queueId;
        this.userId = userId;
        this.fullName = fullName;
    }

    public Person(Person person, User owner){
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.queueId = person.getQueueId();
        this.userId = owner.getId();
    }

    public Person(){}

    public Long getQueueId() {
        return queueId;
    }

    public Long getId() {
        return id;
    }

    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("fullName", fullName)
                .add("queueId", queueId)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }
}
