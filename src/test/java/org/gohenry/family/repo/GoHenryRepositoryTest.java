package org.gohenry.family.repo;

import org.gohenry.family.entities.Parent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoHenryRepositoryTest {

    @Autowired
    private GoHenryRepository repository;

    @Autowired
    public TestEntityManager testEntityManager;



    @Test
    public void getParent_returnParentDetails() throws Exception {
        Parent saveParent = testEntityManager.persistAndFlush(new Parent("go","henry",32));
        Parent parent = repository.findById(1);
        assertThat(parent.getName()).isEqualTo(saveParent.getName() );

    }

    @Test
    public void createParent_returnParentDetails() throws Exception {
        Parent parentToSave = new Parent("go","henry",32);
        Parent saveParent = testEntityManager.persistAndFlush(parentToSave);
        Parent parent = repository.save(parentToSave);
        assertThat(parent.getName()).isEqualTo(saveParent.getName() );

    }

}