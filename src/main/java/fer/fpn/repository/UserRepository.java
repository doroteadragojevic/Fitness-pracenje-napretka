package fer.fpn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.UserFPN;

public interface UserRepository extends JpaRepository<UserFPN, Long>{

}