package org.gohenry.family.services;

import org.gohenry.family.entities.Parent;
import org.gohenry.family.repo.GoHenryRepository;
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
    private GoHenryRepository repository;
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

}