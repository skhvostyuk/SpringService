package ru.tsc.lectures.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "lecture_halls")
public class LectureHall extends NamedEntity {
    @Column(name = "capacity")
    @NotNull
    private int capacity;

    @Column(name = "projector")
    private int projector;


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
    private int price;

    public void update(LectureHall hall) {
        setName(hall.getName());
        setPrice(hall.getPrice());
        setEndTime(hall.getEndTime());
        setStartTime(hall.getStartTime());
        setCapacity(hall.getCapacity());
        setProjector(hall.getProjector());
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getProjector() {
        return projector;
    }

    public void setProjector(int projector) {
        this.projector = projector;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
