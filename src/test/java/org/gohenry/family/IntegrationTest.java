package org.gohenry.family;


import org.gohenry.family.entities.Children;
import org.gohenry.family.entities.Parent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    @Test
    public void getParent_retun404NotFoundError(){
        ResponseEntity<Parent> response = restTemplate.getForEntity("/parent/123",Parent.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void createParent_retunParentDetails(){
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", new Parent("go","createTest",35),Parent.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(response.getBody().getName(), "go");
        Assert.assertEquals(response.getBody().getSurname(), "createTest");
        Assert.assertEquals((int)response.getBody().getAge(), 35);
    }

    @Test
    public void getParentAndChild_retunParentAndChildDetails(){
        ResponseEntity<Parent> response = restTemplate.getForEntity("/parent/1",Parent.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getName(), "go");
        Assert.assertEquals(response.getBody().getSurname(), "henry");
        Assert.assertEquals((int)response.getBody().getAge(), 32);
        Assert.assertEquals(response.getBody().getChildrens().get(0).getName(), "gochild");
    }

    @Test
    public void createParentAndChild_retunParentAndChildDetails(){
        Children child1 = new Children("gochild1","henry", 12);
        Children child2 = new Children("gochild2","henry", 15);
        ArrayList<Children> childrensList = new ArrayList<>();
        childrensList.add(child1);
        childrensList.add(child2);
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", new Parent("go","createWithChildTest",35 , childrensList),Parent.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(response.getBody().getName(), "go");
        Assert.assertEquals(response.getBody().getSurname(), "createWithChildTest");
        Assert.assertEquals(response.getBody().getChildrens().get(0).getName(), "gochild1");
        Assert.assertEquals(response.getBody().getChildrens().get(1).getName(), "gochild2");
        Assert.assertEquals(response.getBody().getChildrens().get(0).getParent().getId(), response.getBody().getId());
        Assert.assertEquals((int)response.getBody().getAge(), 35);
        Assert.assertEquals((int)response.getBody().getAge(), 35);
    }


    @Test
    public void updateParent_retunParentDetails(){

        Map<String, String> param = new HashMap<String, String>();
        param.put("id","2");
        HttpEntity<Parent> requestEntity = new HttpEntity<Parent>(new Parent("goupdated","henryupdated",35), new HttpHeaders());

        HttpEntity<Parent> response = restTemplate.exchange("/parents/{id}", HttpMethod.PUT, requestEntity, Parent.class, param);
        Assert.assertEquals(response.getBody().getName(), "goupdated");
        Assert.assertEquals(response.getBody().getSurname(), "henryupdated");
        Assert.assertEquals((int)response.getBody().getAge(), 35);

    }

    @Test
    public void updateParent_retun404NotFound(){
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","123");
        HttpEntity<Parent> requestEntity = new HttpEntity<Parent>(new Parent("goupdated","henryupdated",35), new HttpHeaders());

        HttpEntity<Parent> response = restTemplate.exchange("/parents/{id}", HttpMethod.PUT, requestEntity, Parent.class, param);
        
        Assert.assertEquals(response.getBody(),null);


    }

    @Test
    public void updateChildren_retunChildrenDetails(){

        Map<String, String> param = new HashMap<String, String>();
        param.put("id","4");
        HttpEntity<Children> requestEntity = new HttpEntity<Children>(new Children("gochildupdated","henrychildupdated",18), new HttpHeaders());

        HttpEntity<Children> response = restTemplate.exchange("/children/{id}", HttpMethod.PUT, requestEntity, Children.class, param);

        Assert.assertEquals(response.getBody().getName(), "gochildupdated");
        Assert.assertEquals(response.getBody().getSurname(), "henrychildupdated");
        Assert.assertEquals((int)response.getBody().getAge(), 18);

    }
}
