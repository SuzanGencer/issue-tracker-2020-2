package com.kodstar.issuetracker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.service.LabelService;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import com.kodstar.issuetracker.utils.impl.FromLabelToLabelDTO;
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

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    LabelService labelService;
    @MockBean
    FromIssueToIssueDTO issueConverter;
    @MockBean
    FromLabelToLabelDTO fromLabelToLabelDTO;
    private Issue issue;
    ObjectMapper objectMapper = new ObjectMapper();
    private IssueDTO issueDTO;

    @BeforeEach
    void setUp() {
        Label label = new Label(450L, "testLabel");
        Set<Label> labels = new HashSet<>();
        labels.add(label);
        issue = new Issue();
        issue.setId(777L);
        issue.setTitle("Test");
        issue.setDescription("Test text");
        issue.setLabels(labels);

        LabelDTO labelDTO = new LabelDTO(45L, "testLabelDTO");
        Set<LabelDTO> labelDTOSet = new HashSet<>();
        labelDTOSet.add(labelDTO);
        issueDTO = new IssueDTO(777L, "testTitle", "desc Test", labelDTOSet);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestCreateIssueShouldReturnAJsonObject() throws Exception {
        Mockito.when(issueService.createIssue(Mockito.any(IssueDTO.class))).thenReturn(issueDTO);
        Mockito.when(issueConverter.convert(Mockito.any(Issue.class))).thenReturn(issueDTO);
        mvc.perform(MockMvcRequestBuilders.post("/issue")
                .content(asJsonString(issue))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(777)))
                .andExpect(jsonPath("$.title", is(issueDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(issueDTO.getDescription())));
    }

    @Test
    public void TestGetAllLabelsShouldReturnAJsonObject() throws Exception {
        LabelDTO label1 = new LabelDTO(45L, "testLabel1");
        LabelDTO label2 = new LabelDTO(55L, "testLabel2");
        LabelDTO label3 = new LabelDTO(65L, "testLabel3");

        Set<LabelDTO> labelDTOSet = new HashSet<>();

        labelDTOSet.add(label1);
        labelDTOSet.add(label2);
        labelDTOSet.add(label3);

        Mockito.when(labelService.getAllLabels()).thenReturn(labelDTOSet);

        mvc.perform(MockMvcRequestBuilders.get("/issues/labels")
                .content(asJsonString(labelDTOSet))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createIssue_unsuccesfull_if_title_attribute_is_null() throws Exception {
        issueDTO.setTitle(null);
        mvc.perform(post("/issue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(issueDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllIssuesShouldReturnAJsonObject() throws Exception {
        List<IssueDTO> issueList = new ArrayList<>();
        issueList.add(issueDTO);
        List<IssueDTO> issueDTOList = new ArrayList<>();
        issueDTOList.add(issueDTO);
        Mockito.when(issueService.getAllIssues()).thenReturn(issueList);
        Mockito.when(issueConverter.convertAll(Mockito.any(List.class))).thenReturn(issueDTOList);
        mvc.perform(MockMvcRequestBuilders.get("/issues")
                .content(asJsonString(issue))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is(issueDTOList.get(0).getTitle())))
                .andExpect(jsonPath("$[0].id", is(777)))
                .andExpect(jsonPath("$[0].description", is(issueDTOList.get(0).getDescription())));
    }
}