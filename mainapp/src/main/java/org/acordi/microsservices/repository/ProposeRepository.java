package org.acordi.microsservices.repository;

import org.acordi.microsservices.entity.Propose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposeRepository extends CrudRepository<Propose, Long> {

}
