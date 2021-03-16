package com.ipiecoles.batch.dbexport;

import com.ipiecoles.batch.csvImport.HelloWorldTasklet;
import com.ipiecoles.batch.dto.CommuneDto;
import com.ipiecoles.batch.model.Commune;
import com.ipiecoles.batch.repository.CommuneRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class CommunesDBExportBatch {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public CommuneRepository communeRepository;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet countFileTasklet(){
        return new CountFileTasklet();
    }

    @Bean
    public Step stepCountFileTasklet(){
        return stepBuilderFactory.get("stepCountFileTasklet").tasklet(countFileTasklet())
                .build();
    }

    @Bean
    @Qualifier("repositoryCommuneReader")
    public RepositoryItemReader<Commune> repositoryCommuneReader() {
        Map<String, Sort.Direction> map = new HashMap<>();
        map.put("codePostal", Sort.Direction.DESC);
        RepositoryItemReader<Commune> repositoryItemReader = new RepositoryItemReader<>();
        repositoryItemReader.setRepository(communeRepository);
        repositoryItemReader.setMethodName("findAll");
        repositoryItemReader.setSort(map);
        System.out.println(map);
        return repositoryItemReader;
    }

    @Bean
    @Qualifier("exportCommunes")
    public Job exportCommunes(Step stepCountFileTasklet){
        return jobBuilderFactory.get("exportCommunes")
                .incrementer(new RunIdIncrementer())
                .flow(stepCountFileTasklet())
                .end()
                .build();
    }
}
