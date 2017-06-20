package br.com.nozella.springbatchapp.processors;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Marcos
 * on 19/06/2017 20:56.
 */
public class CustomProcessor implements ItemProcessor<Report, Report> {

    @Override
    public Report process(final Report item) throws Exception {
        System.out.println("processando item " + item.toString());
        return item;
    }
}
