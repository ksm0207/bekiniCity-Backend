package api.app.bekiniCity.config;

import api.app.bekiniCity.entity.Board;
import api.app.bekiniCity.entity.Category;
import api.app.bekiniCity.entity.User;
import api.app.bekiniCity.repository.BoardRepository;
import api.app.bekiniCity.repository.CategoryRepository;
import api.app.bekiniCity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    public DataInitializer(UserRepository userRepository, CategoryRepository categoryRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize dummy data
        initializeUsers();
        initializeCategories();
        initializeBoards();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            User[] users = {
                    new User(null, "John Doe", 30, "Male", LocalDate.of(1994, 1, 1), "USA", LocalDateTime.now(), LocalDateTime.now()),
                    new User(null, "Jane Smith", 28, "Female", LocalDate.of(1996, 5, 15), "Canada", LocalDateTime.now(), LocalDateTime.now()),
                    new User(null, "Alex Johnson", 35, "Male", LocalDate.of(1988, 12, 20), "UK", LocalDateTime.now(), LocalDateTime.now()),
                    new User(null, "Emily Davis", 22, "Female", LocalDate.of(2002, 7, 5), "Australia", LocalDateTime.now(), LocalDateTime.now()),
                    new User(null, "Michael Brown", 40, "Male", LocalDate.of(1983, 11, 11), "Germany", LocalDateTime.now(), LocalDateTime.now())
            };
            userRepository.saveAll(Arrays.asList(users));  // Save all users in a single batch
        }
    }

    private void initializeCategories() {
        if (categoryRepository.count() == 0) {
            Category[] categories = {
                    new Category(null, "C_COMM", "Community", "커뮤니티"),
                    new Category(null, "C_TEMP", "Water temperature", "수온 정보"),
                    new Category(null, "C_LOST", "Lost property", "분실물")
            };
            categoryRepository.saveAll(Arrays.asList(categories));  // Save all categories in a single batch
        }
    }

    private void initializeBoards() {
        if (boardRepository.count() == 0) {
            User[] users = userRepository.findAll().toArray(new User[0]);
            Category[] categories = categoryRepository.findAll().toArray(new Category[0]);

            LocalDateTime now = LocalDateTime.now();  // Use a consistent timestamp

            // Create 50 dummy posts
            List<Board> boards = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                User randomUser = users[i % users.length];
                Category randomCategory = categories[i % categories.length];

                String title = "Post #" + (i + 1);
                String content = generateContentBasedOnCategory(randomCategory.getCode(), i + 1);

                Board board = new Board(null, randomCategory, randomUser, title, content,
                        false, now, now);
                boards.add(board);
            }
            boardRepository.saveAll(boards);  // Save all boards in a single batch
        }
    }

    private String generateContentBasedOnCategory(String categoryCode, int postNumber) {
        return switch (categoryCode) {
            case "C_COMM" -> "This is community post #" + postNumber + ". Share your thoughts, ideas, and experiences with others here!";
            case "C_TEMP" -> "This is water temperature post #" + postNumber + ". Stay informed about the latest water temperature readings in your area!";
            case "C_LOST" -> "This is a lost property post #" + postNumber + ". If you've lost something, this is the place to report it!";
            default -> "This is a default post #" + postNumber + ". Please check back for more updates.";
        };
    }
}