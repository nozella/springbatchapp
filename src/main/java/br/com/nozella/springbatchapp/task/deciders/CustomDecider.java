package br.com.nozella.springbatchapp.task.deciders;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

/**
 * Created by Marcos Nozella
 * on 19/06/2017 23:05.
 */
@Component
public class CustomDecider implements JobExecutionDecider {

    @SuppressWarnings("unused")
    private static final String ABORT = "ABORT";
    private static final String CONTINUE = "CONTINUE";

    @Override
    public FlowExecutionStatus decide(final JobExecution jobExecution, final StepExecution stepExecution) {
        final ExecutionContext executionContext = jobExecution.getExecutionContext();
        final String executionStatus = CONTINUE;
        executionContext.put("executionStatus", executionStatus);
        return new FlowExecutionStatus(executionStatus);
    }
}
