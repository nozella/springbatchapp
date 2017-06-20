package br.com.nozella.springbatchapp.writers;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CustomWriter implements ItemWriter<Report> {

    private StepExecution stepExecution;

    @BeforeStep
    public void saveStepExecution(final StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

	@Override
	public void write(List<? extends Report> items) throws Exception {

		System.out.println("writer..." + items.size());		
		for(Report item : items){
			System.out.println(item);
		}

        final ExecutionContext stepContext = this.stepExecution.getExecutionContext();
        stepContext.put("items", items);
	}

}