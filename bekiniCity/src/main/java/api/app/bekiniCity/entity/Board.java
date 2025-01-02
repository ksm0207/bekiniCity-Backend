package api.app.bekiniCity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// JPA Entity 지정
// 해당 클래스 기준으로 데이터베이스 테이블을 매핑한다.
@Entity
@Table(name = "tb_board")
@Getter
@Setter
public class Board {
    // 1. 기본 키값 자동생성 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Maps to an auto-increment primary key
    private int idx;

    // 2. 테이블 컬럼 정의
    @Column(nullable = false, length = 10 )
    private String categoryId;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT" )
    private String content;


    // TINYINT(1)은 MySQL에서 Boolean처럼 사용가능
    @Column(name = "del_yn", columnDefinition = "TINYINT(1)")
    private Boolean delYn;
    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            // 엔티티가 생성되기 전 현재 시간을 설정한다(DB에서 시간처리)
            createTime = new Date();
        }
    }
}
