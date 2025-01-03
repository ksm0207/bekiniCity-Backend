package api.app.bekiniCity.controller;

import api.app.bekiniCity.entity.Board;
import api.app.bekiniCity.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/api/board")
@RestController
public class BoardController {

    private final BoardService service;
    @Autowired
    public BoardController(BoardService boardService) {
        this.service = boardService;
    }

    // INSERT
    @PostMapping("/add")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        System.out.println("API Board Add !");
        Board save = service.createBoard(board);
        return ResponseEntity.ok(save);
    }
    // SELECT
    @PostMapping("/all")
    public ResponseEntity<List<Board>> selectAll() {
        System.out.println("API Board select All !");

        List<Board> allItems = service.getAllBoards();
        return ResponseEntity.ok(allItems);
    }
    // UPDATE

    // DELETE
}
