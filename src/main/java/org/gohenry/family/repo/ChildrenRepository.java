package org.gohenry.family.repo;

import org.gohenry.family.entities.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {

    public Children findById(long id);

    public Children save(Children children);
}
