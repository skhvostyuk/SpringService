package ru.tsc.lectures.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import ru.tsc.lectures.LecturesApplication;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.repository.LectureRepository;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;


@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@WebMvcTest
@WebMvcTest(LectureController.class)
@AutoConfigureRestDocs(outputDir = "target/generated_snippets")
public class LectureControllerTest {

    private static final int TEST_LECTURE_ID = 1;
    private static final String TEST_LECTURE_NAME = "Lecture";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureRepository repository;

    private Lecture lecture;

    @Before
    public void setup() {

        lecture = new Lecture();
        lecture.setId(TEST_LECTURE_ID);
        lecture.setName(TEST_LECTURE_NAME);
        lecture.setCapacity(20);
        lecture.setDuration(120);
        lecture.setLecturerName("Prof. Tester");
        given(this.repository.findById(TEST_LECTURE_ID)).willReturn(lecture);
        ArrayList<Lecture> list = new ArrayList<>();
        list.add(lecture);
        given(this.repository.findByName(TEST_LECTURE_NAME)).willReturn(list);

    }

    @Test
    public void getByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/lecture/" + TEST_LECTURE_ID)
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(TEST_LECTURE_NAME))
                .andDo(document("{class-name}/{method-name}"));
    }

    @Test
    public void getByNameTest() throws Exception {
        this.mockMvc.perform(get("/api/lecture?name=" + TEST_LECTURE_NAME)
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(TEST_LECTURE_NAME));
    }


}