package com.ipiecoles.batch.csvImport;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldTasklet implements Tasklet {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String message = null;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello world");
        message = "Hello World !";
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
        sExec.getJobExecution().getExecutionContext().put("MSG", message);
        logger.info("After Tasklet Hello Wolrd");
        logger.info(sExec.getSummary());
        return ExitStatus.COMPLETED;
    }

}
