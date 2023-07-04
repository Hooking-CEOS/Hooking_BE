package shop.hooking.hooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.hooking.hooking.entity.Have;

import java.util.List;

public interface HaveRepository extends JpaRepository<Have, Long> {
    List<Have> findByBrandId(Long brand_id);
}
