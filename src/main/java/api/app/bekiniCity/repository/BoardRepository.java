package api.app.bekiniCity.repository;

import api.app.bekiniCity.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    // Board Entity와 상호작용 및 DB 연결을 담당한다.
    // Board , Integer를 상속받으면 Board Entity에 대한 기본적인
    // CRUD 기능을 자동생성 가능하다 또한 Integer는 해당 Entity의 기본키이다
}
