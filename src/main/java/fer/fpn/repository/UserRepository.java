package fer.fpn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.User;

public interface UserRepository extends JpaRepository<User, Long>{

}