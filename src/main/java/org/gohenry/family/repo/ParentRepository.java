package org.gohenry.family.repo;

import org.gohenry.family.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    public Parent findById(long id);

    public Parent save(Parent parent);
}
