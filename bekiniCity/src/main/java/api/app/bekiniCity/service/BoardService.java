package api.app.bekiniCity.service;

import api.app.bekiniCity.entity.Board;
import api.app.bekiniCity.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    // 1. 게시글 등록
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 2. 게시판 전체조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}
