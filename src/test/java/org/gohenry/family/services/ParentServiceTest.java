package org.gohenry.family.services;

import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.repo.ParentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ParentServiceTest {


    @Mock
    private ParentRepository repository;
    private ParentService parentService;

    @Before
    public void setUp() throws Exception{
        parentService = new ParentService(repository);
    }

    @Test
    public void getParentDetail_returnParentDetails() throws Exception {

        given(repository.findById(1)).willReturn(new Parent("go","henry",32));
        Parent parent = parentService.getParentDetails(1);

        Assert.assertEquals(parent.getName(), "go");
        Assert.assertEquals(parent.getSurname(), "henry");
        Assert.assertEquals((int)parent.getAge(), 32);

    }

    @Test (expected = ParentNotFoundExcepction.class)
    public void getParentDetail_whenParentNotFound(){
        given(repository.findById(2)).willReturn(null);
        Parent parent = parentService.getParentDetails(1);

    }

    @Test
    public void createParent(){
        Parent parent = new Parent("go","henry", 32);
        given(repository.save(parent)).willReturn(parent);
        Parent parentReturn = parentService.createParent(parent);
        assertEquals(parent.getName(),parentReturn.getName());
        assertEquals(parent.getSurname(),parentReturn.getSurname());
        assertEquals(parent.getAge(),parentReturn.getAge());

    }

}