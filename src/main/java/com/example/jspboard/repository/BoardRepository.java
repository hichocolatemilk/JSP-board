package com.example.jspboard.repository;

import com.example.jspboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.hit = b.hit +1 where b.id = :id ")
    int updateHit(@Param("id") Long id);


    Page<Board> findByTitle(String searchTitle, Pageable pageable);

}
