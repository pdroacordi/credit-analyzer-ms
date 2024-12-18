package org.acordi.microsservices.repository;

import org.acordi.microsservices.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByCpf(String cpf);
}
