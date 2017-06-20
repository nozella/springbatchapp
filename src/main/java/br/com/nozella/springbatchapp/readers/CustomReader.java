package br.com.nozella.springbatchapp.readers;

import br.com.nozella.springbatchapp.model.Report;
import org.springframework.batch.item.ItemReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcos
 * on 19/06/2017 21:00.
 */

public class CustomReader implements ItemReader<Report> {

    private List<Report> reportList;

    @Override
    public Report read() throws Exception {
        if (this.reportList == null) {
            this.loadReportList();
        }
        if (reportList.size() == 0) {
            System.out.println("acabou a leitura");
            return null;
        }
        final Report report = reportList.remove(0);
        System.out.println("lendo item " + report);
        return report;
    }

    private void loadReportList() {
        System.out.println("obtendo dados");
        reportList = new ArrayList<>();
        Report report;
        report = new Report();
        report.setId(12);
        report.setString("123,456");
        reportList.add(report);
        report = new Report();
        report.setId(13);
        report.setString("234,567");
        reportList.add(report);
        report = new Report();
        report.setId(14);
        report.setString("345,678");
        reportList.add(report);
    }
}
