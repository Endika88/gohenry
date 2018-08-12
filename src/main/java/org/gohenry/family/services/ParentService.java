package org.gohenry.family.services;


import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.repo.GoHenryRepository;
import org.springframework.stereotype.Service;


@Service
public class ParentService {


    private GoHenryRepository repository;

    public ParentService(GoHenryRepository repository) {

        this.repository = repository;
    }

    public Parent getParentDetails(Integer id) {

        Parent parent = repository.findById(id);

        if(parent==null){
            throw new ParentNotFoundExcepction();
        }
        return parent;
    }
}
