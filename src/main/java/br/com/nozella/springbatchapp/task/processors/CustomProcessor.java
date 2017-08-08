package br.com.nozella.springbatchapp.task.processors;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Marcos Nozella
 * on 19/06/2017 20:56.
 */
@Component
public class CustomProcessor implements ItemProcessor<Report, Report> {

    @Override
    public Report process(final Report item) throws Exception {
        System.out.println("processando item " + item.toString());
        return item;
    }
}
