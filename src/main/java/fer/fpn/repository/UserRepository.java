package fer.fpn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.Exercise;
import fer.fpn.dao.User;

public interface UserRepository extends JpaRepository<User, Long>{

}