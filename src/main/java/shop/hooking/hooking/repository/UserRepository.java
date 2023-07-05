package shop.hooking.hooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.hooking.hooking.entity.User;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findMemberByKakaoId(Long kakaoId);

   // Optional<User> findMemberByMemberIdAndDeleteFlagIsFalse(Long memberId);

}



