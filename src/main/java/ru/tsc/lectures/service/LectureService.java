package ru.tsc.lectures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.repository.LectureRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class LectureService {
    @Autowired
    private LectureRepository repository;

    /**
     * Returns true if new lecture's time don't overlapping other lecture's time and
     * lecture was saved and false if not.
     */
    public boolean newLecture(Lecture lecture) {
        LectureHall hall = lecture.getLectureHall();
        for (Lecture l : hall.getLectures())
            if (isOverlapping(l.getDate(), l.getDuration(), lecture.getDate(), lecture.getDuration()))
                return false;
        repository.save(lecture);
        return true;
    }

    public static boolean isOverlapping(LocalDateTime start1, int duration1,
                                        LocalDateTime start2, int duration2) {
        return !start1.isAfter(start2.plusMinutes(duration2)) &&
                !start2.isAfter(start1.plusMinutes(duration1));
    }

    public Lecture findById(int lectureId) {
        return repository.findById(lectureId);
    }

    public Collection<Lecture> findAll() {
        return repository.findAll();
    }

    public Collection<Lecture> findByFilter(String name, int minPrice, int maxPrice) {
        return repository.findByNameContainingAndPriceBetween(name, minPrice, maxPrice);
    }

    public void delete(Lecture lecture) {
        repository.delete(lecture);
    }
}
