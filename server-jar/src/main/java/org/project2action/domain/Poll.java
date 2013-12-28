package org.project2action.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "POLLS")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    @JsonCreator
    public Poll(@JsonProperty("id") Long id,
                 @JsonProperty("title") String title){
        this.id = id;
        this.title = title;
    }
    public Poll(){}

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
