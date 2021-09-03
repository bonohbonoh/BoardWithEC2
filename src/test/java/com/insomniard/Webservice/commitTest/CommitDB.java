package com.insomniard.Webservice.commitTest;

import com.insomniard.Webservice.Account.domain.User;
import com.insomniard.Webservice.Account.domain.UserRepository;
import com.insomniard.Webservice.Board.Repository.BoardRepository;
import com.insomniard.Webservice.Board.entity.Board;
import com.insomniard.Webservice.commit.entity.Commit;
import com.insomniard.Webservice.commit.repository.CommitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommitDB {
    @Autowired
    private CommitRepository commitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 댓글연결이되었나요(){
        LocalDateTime now = LocalDateTime.of(2021, 8, 31, 17, 39);
        Commit commit = Commit.builder()
                .contents("b")
                .author("c")
                .build();
        commitRepository.save(commit);
        List<Commit> list = commitRepository.findAll();
        Commit Time = list.get(0);
        System.out.println("=============================================\n" +
                "registtTime : " + Time.getRegistrationTime() + "\tupdateTime : " + Time.getUpdateTime()
                + "=============================================");
        assertThat(Time.getRegistrationTime().isAfter(now));
        assertThat(Time.getUpdateTime().isAfter(now));

    }

    @Test
    public void 댓글테스트(){
        User user = userRepository.findByUserId(1L);
        IntStream.rangeClosed(0, 100).forEach(i ->{
            long boardId = (long)(Math.random() * 100) + 1;
            Board board = boardRepository.findByBoardId(boardId);
            Commit commit = Commit.builder()
                    .contents("test commit" + i)
                    .board(board)
                    .author(user.getName())
                    .build();
            commitRepository.save(commit);
        });
    }

    @Test
    public void 게시글댓글조회(){
            Optional<Commit> test = commitRepository.findById(50L);
            Commit commit = test.get();
            System.out.println("Commit : " + commit.toString());
    }
}

