package com.example.hellospringbatch.configuration;

import com.example.hellospringbatch.Entity.Expenditure;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 특정 사용자 + 카테고리에 따른 지출 내역과 총합 가져오기
 * 예제) 이메일이 email...1이고 카테고리가 FOOD인 지출 내역과 총합
 *
 */

//@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
@Log4j2
public class JobConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final int chunkSize = 5;
    private final DataSource dataSource;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchjob2")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Expenditure, Expenditure>chunk(chunkSize)
                .reader(jpaReader())
                .writer(jpaWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Expenditure> jpaReader() throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "email...1"); // email...1
        parameters.put("category", 0); // FOOD

        return new JdbcPagingItemReaderBuilder<Expenditure>()
                .name("jpaReader")
                .pageSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Expenditure.class))
                .queryProvider(createQueryProvider())
                .parameterValues(parameters)
                .build();

    }

    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();

        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("id, user_email, amount, category, date, environment");
        queryProvider.setFromClause("from expenditure");
        queryProvider.setWhereClause("where user_email = :email and category = :category");

        Map<String, Order> sortKeys = new HashMap<>(2);
        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        return queryProvider.getObject();

    }

    @Bean
    public ItemWriter<? super Expenditure> jpaWriter() {

        AtomicInteger totalAmount = new AtomicInteger();

        return items -> {
            System.out.println();
            for (Expenditure expenditure : items) {
                totalAmount.addAndGet(expenditure.getAmount());
                System.out.println(expenditure);
                System.out.println();
            }
            System.out.println("총 지출 금액: " + totalAmount + " 원");
        };
    }

}
