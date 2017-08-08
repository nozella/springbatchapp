package br.com.nozella.springbatchapp.task.tasklets;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Marcos Nozella
 * on 19/06/2017 21:54.
 */
@Component
public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final String executionStatus = (String) chunkContext.getStepContext().getJobExecutionContext().get("executionStatus");
        if ("ABORT".equals(executionStatus)) {
            System.out.println("status abort");
            return RepeatStatus.FINISHED;

        }
        @SuppressWarnings({"unchecked"})
        List<Report> reportList = (List<Report>) chunkContext.getStepContext().getJobExecutionContext().get("items");
        System.out.println("Tasklet itens recebidos " + reportList);
        return RepeatStatus.FINISHED;
    }

}
