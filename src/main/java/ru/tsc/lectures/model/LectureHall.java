package ru.tsc.lectures.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "lecture_halls")
public class LectureHall extends NamedEntity {
    @Column(name = "capacity")
    @NotEmpty
    private int capacity ;

    @Column(name = "projector")
    private boolean projector;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lectureHall")
    @JsonManagedReference
    private Set<Lecture> lectures;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime endTime;

    @Column(name = "price")
    @DateTimeFormat(pattern = "hh:mm")
    private int price;

}
