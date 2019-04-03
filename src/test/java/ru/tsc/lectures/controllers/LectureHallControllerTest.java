package ru.tsc.lectures.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.repository.LectureHallRepository;
import ru.tsc.lectures.repository.LectureRepository;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LectureHallController.class)
@AutoConfigureRestDocs(outputDir = "target/generated_snippets")
public class LectureHallControllerTest {
    private static final int TEST_HALL_ID = 1;
    private static final String TEST_HALL_NAME = "First Hall";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LectureHallRepository repository;

    private LectureHall lectureHall;

    @Before
    public void setup() {
        lectureHall = new LectureHall();
        lectureHall.setId(TEST_HALL_ID);
        lectureHall.setName(TEST_HALL_NAME);
        lectureHall.setCapacity(200);
        lectureHall.setProjector(true);
        lectureHall.setStartTime(LocalTime.parse("09:00"));
        lectureHall.setEndTime(LocalTime.parse("20:00"));
        lectureHall.setPrice(1000);
        given(this.repository.findById(TEST_HALL_ID)).willReturn(lectureHall);
        ArrayList<Lecture> list = new ArrayList<>();
        //list.add(lecture);
        //given(this.repository.findByName(TEST_LECTURE_NAME)).willReturn(list);
    }

    @Test
    public void addHallTest() throws Exception {
        lectureHall.setId(10);
        ObjectMapper mapper = new ObjectMapper();
        String hallAsJSON = mapper.writeValueAsString(lectureHall);
        this.mockMvc.perform(post("/api/hall/")
                .content(hallAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andDo(document("{class-name}/{method-name}"));
    }
}
