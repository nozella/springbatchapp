package br.com.nozella.springbatchapp.configuration;

import br.com.nozella.springbatchapp.model.Report;
import br.com.nozella.springbatchapp.task.deciders.CustomDecider;
import br.com.nozella.springbatchapp.task.processors.CustomProcessor;
import br.com.nozella.springbatchapp.task.readers.CustomReader;
import br.com.nozella.springbatchapp.task.tasklets.CustomTasklet;
import br.com.nozella.springbatchapp.task.writers.CustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Marcos Nozella
 * on 08/08/2017.
 */
@Configuration
@EnableBatchProcessing
@ComponentScan("br.com.nozella.springbatchapp")
public class BatchConfiguration {

    @Bean
    public Job job(
            final JobBuilderFactory jobBuilderFactory,
            final Step taskletStep,
            final Step processStep,
            final CustomDecider customDecider) {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        final Flow flow = flowBuilder
                .from(customDecider).on("CONTINUE").to(processStep).next(taskletStep)
                .from(customDecider).on("ABORT").to(taskletStep).end();
        return jobBuilderFactory.get("job").start(flow).end().build();
    }

    @Bean
    public Step taskletStep(
            final StepBuilderFactory stepBuilderFactory,
            final CustomTasklet customTasklet) {
        return stepBuilderFactory.get("taskletStep").tasklet(customTasklet).build();
    }

    @Bean
    public Step processStep(
            final StepBuilderFactory stepBuilderFactory,
            final CustomWriter customWriter,
            final CustomProcessor customProcessor,
            final CustomReader customReader) {
        final StepBuilder step = stepBuilderFactory.get("processStep");
        final SimpleStepBuilder<Report, Report> chunk = step.chunk(10);
        return chunk.reader(customReader)
                .processor(customProcessor)
                .writer(customWriter)
                .listener(buildListener())
                .build();
    }

    private ExecutionContextPromotionListener buildListener() {
        final ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        final String[] keys = {"items"};
        listener.setKeys(keys);
        return listener;
    }
}
