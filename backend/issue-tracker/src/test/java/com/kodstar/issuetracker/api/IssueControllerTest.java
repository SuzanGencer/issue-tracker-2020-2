package com.kodstar.issuetracker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.IssueConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(IssueController.class)
@AutoConfigureMockMvc
class IssueControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    IssueService issueService;
    @MockBean
    IssueConverter issueConverter;
    private Issue issue;
    ObjectMapper objectMapper=new ObjectMapper();

    @BeforeEach
    void setUp() {
        issue = new Issue();
        issue.setDescription("desc2");
        issue.setTitle("tittle");
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestCreateIssueShouldReturnAJsonObject() throws Exception {
        Label label= new Label(45L,"testLabel");
        Set<Label> setLabel= new HashSet<>();
        setLabel.add(label);

        Issue issue = new Issue(777L,"testTitle", "desc Test", setLabel);
        Mockito.when(issueService.createIssue(Mockito.any(Issue.class))).thenReturn(issue);
        mvc.perform(MockMvcRequestBuilders.post("/issue")
                .content(asJsonString(issue))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
    @Test
    void createIssue_unsuccesfull_if_false_attribute_name() throws Exception {
        mvc.perform(post("/issue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titles\":\"Titttle\",\"description\":\"desc2\"}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void createIssue_unsuccesfull_if_attribute_is_null() throws Exception {
        issue.setTitle(null);
        mvc.perform(post("/issue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(issue)))
                .andExpect(status().isBadRequest());
    }
}