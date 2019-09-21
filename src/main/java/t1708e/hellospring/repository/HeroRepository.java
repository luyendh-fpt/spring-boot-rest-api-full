package t1708e.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import t1708e.hellospring.entity.Hero;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

    @Query("select h from Hero as h where h.status = :status")
    List<Hero> findActiveHero(@Param("status") int status);

    List<Hero> findAllByStatus(int status);

}