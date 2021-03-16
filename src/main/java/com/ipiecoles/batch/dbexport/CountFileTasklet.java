package com.ipiecoles.batch.dbexport;

import com.ipiecoles.batch.repository.CommuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class CountFileTasklet implements Tasklet {

    @Autowired
    CommuneRepository communeRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long countCp = null;;
    public Long countCommune = null;;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        countCp = communeRepository.countDistinctCodePostal();
        countCommune = communeRepository.countCommune();
        return RepeatStatus.FINISHED;
    }

    @BeforeStep
    public void beforeStep(StepExecution sExec) throws Exception {
        //Avant l'exécution de la Step
        logger.info("Before Tasklet Hello Wolrd");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution sExec) throws Exception {
        //Une fois la Step
        sExec.getJobExecution().getExecutionContext().put("CountCp", countCp);
        sExec.getJobExecution().getExecutionContext().put("CountCommune", countCommune);
        logger.info(sExec.getSummary());
        System.out.println("pougfiodr");
        return ExitStatus.COMPLETED;
    }
}
