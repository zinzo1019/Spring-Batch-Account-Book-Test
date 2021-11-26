package com.example.hellospringbatch.RepositoryTest;

import com.example.hellospringbatch.Entity.Expenditure;
import com.example.hellospringbatch.Entity.User;
import com.example.hellospringbatch.Entity.UserCategory;
import com.example.hellospringbatch.Repository.ExpenditureRepository;
import com.example.hellospringbatch.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    Random random = new Random();

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(0, 100).forEach(i -> {

            int temp = i % 20 + 1;

            // user 먼저 생성
            User user = User.builder()
                    .email("email..." + temp)
                    .build();

            if (i < 21)
                userRepository.save(user);

            Expenditure expenditure = Expenditure.builder()
                    .amount(((int)(Math.random() * (10000))) * 100)
                    .environment(random.nextBoolean())
                    .category(category())
                    .user(user)
                    .build();

            expenditureRepository.save(expenditure);

        });

    }

    public UserCategory category() {
        int num = random.nextInt(4);
        switch (num) {
            case 0:
                return UserCategory.HEALTH;
            case 1:
                return UserCategory.FOOD;
            case 2:
                return UserCategory.BEAUTY;
            case 3:
                return UserCategory.EDUCATION;
            default:
                return null;
        }
    }
    
}
