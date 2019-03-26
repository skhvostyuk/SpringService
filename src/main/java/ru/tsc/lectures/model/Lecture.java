package ru.tsc.lectures.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "lectures")
public class Lecture extends NamedEntity {

    @Column(name = "lecturer_name")
    @NotEmpty
    private String lecturerName;

    @Column(name = "capacity")
    @NotEmpty
    private int capacity ;

    @Column(name = "date")
    @NotEmpty
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDate date;

    @Column(name = "duration")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "lecture_hall_id")
    @JsonBackReference
    private LectureHall lectureHall;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    @JsonManagedReference
    private Set<Visitor> visitors;

    Lecture() {
        this.date = LocalDate.now();
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected Set<Visitor> getVisitorsInternal() {
        if (this.visitors == null) {
            this.visitors = new HashSet<>();
        }
        return this.visitors;
    }

    protected void setVisitorsInternal(Set<Visitor> pets) {
        this.visitors = pets;
    }


    public List<Visitor> getVisitors() {
        List<Visitor> sortedPets = new ArrayList<>(getVisitorsInternal());
        PropertyComparator.sort(sortedPets,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Visitor visitor) {
        if (visitor.isNew()) {
            getVisitorsInternal().add(visitor);
        }
        visitor.setLecture(this);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LectureHall getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(LectureHall lectureHall) {
        this.lectureHall = lectureHall;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
