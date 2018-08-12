package org.gohenry.family.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.services.ParentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;

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

    @Test
    public void getParent_ShouldReturn404NotFoundError() throws Exception{

        given(parentService.getParentDetails(Matchers.anyInt())).willThrow(new ParentNotFoundExcepction());
        mockMvc.perform(MockMvcRequestBuilders.get("/parent/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void createParent_ShouldReturnParent() throws Exception{

        Parent parent = new Parent();
        parent.setName("go");
        parent.setSurname("createParentControllerTest");
        parent.setAge(33);
        //... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(parent );
        given(parentService.createParent(parent)).willReturn(parent);
        mockMvc.perform(MockMvcRequestBuilders.post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("surname").value("createParentControllerTest"));

    }

    @Test
    public void createParent_ShouldReturn500Error() throws Exception{

        Parent parent = new Parent();
        parent.setName("go");
        parent.setSurname("createParentController500Test");
        parent.setAge(33);
        //... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(parent );
        given(parentService.createParent(anyObject())).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }
}
