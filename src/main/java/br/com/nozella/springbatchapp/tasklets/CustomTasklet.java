package br.com.nozella.springbatchapp.tasklets;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

/**
 * Created by Marcos
 * on 19/06/2017 21:54.
 */
public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final String executionStatus = (String) chunkContext.getStepContext().getJobExecutionContext().get("executionStatus");
        if ("ABORT".equals(executionStatus)) {
            System.out.println("status abort");
            return RepeatStatus.FINISHED;

        }
        List<Report> reportList = (List<Report>) chunkContext.getStepContext().getJobExecutionContext().get("items");
        System.out.println("Tasklet itens recebidos " + reportList);
        return RepeatStatus.FINISHED;
    }

}
