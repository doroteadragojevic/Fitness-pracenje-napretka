package fer.fpn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fer.fpn.dao.UserFPN;
public interface UserRepository extends JpaRepository<UserFPN, Long>{
	Optional<UserFPN> findFirstByUserIdLessThanOrderByUserIdDesc(Long userId);
    Optional<UserFPN> findFirstByUserIdGreaterThanOrderByUserIdAsc(Long userId);
	Optional<UserFPN> findByEmail(String string);
}