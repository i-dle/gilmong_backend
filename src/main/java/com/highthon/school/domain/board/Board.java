package com.highthon.school.domain.board;

import com.highthon.school.domain.board.dto.CreateBoardRequestDto;
import com.highthon.school.domain.honey.Honey;
import com.highthon.school.domain.job.Jab;
import com.highthon.school.domain.user.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Step step;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Honey> honeyList;

    @ManyToOne
    private Jab jab;

    @CreatedDate
    private LocalDateTime createdAt;

    public Board(CreateBoardRequestDto createBoardRequest, User user, Jab jab) {
        this.title = createBoardRequest.getTitle();
        this.content = createBoardRequest.getContent();
        this.step = createBoardRequest.getStep();
        this.user = user;
        this.jab = jab;
    }
}
