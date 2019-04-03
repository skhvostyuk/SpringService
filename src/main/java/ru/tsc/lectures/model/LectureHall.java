package ru.tsc.lectures.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private int price;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
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
