package org.gohenry.family;


import org.gohenry.family.entities.Parent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void getParent_retunParentDetails(){
        ResponseEntity<Parent> response = restTemplate.getForEntity("/parent/1",Parent.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getName(), "go");
        Assert.assertEquals(response.getBody().getSurname(), "henry");
        Assert.assertEquals((int)response.getBody().getAge(), 32);
    }
}