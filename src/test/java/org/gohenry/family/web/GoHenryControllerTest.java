package org.gohenry.family.web;

import org.gohenry.family.entities.Parent;
import org.gohenry.family.services.ParentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(GoHenryController.class)
@RunWith(SpringRunner.class)
public class GoHenryControllerTest {


    @Autowired
    MockMvc mockMvc;
    @MockBean
    ParentService parentService;

    @Test
    public void getParent_ShouldReturnParent() throws Exception{

        given(parentService.getParentDetails(Matchers.anyInt())).willReturn(new Parent("go","henry", 32));
        mockMvc.perform(MockMvcRequestBuilders.get("/parent/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("go"))
                .andExpect(MockMvcResultMatchers.jsonPath("surname").value("henry"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value("32"));


    }
}
