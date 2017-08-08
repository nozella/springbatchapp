package br.com.nozella.springbatchapp.main;

import br.com.nozella.springbatchapp.configuration.BatchConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Marcos Nozella
 */
@Component
public class App extends Thread {

	public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);
        final App app = context.getBean(App.class);
        app.run();
	}

	private final JobLauncher jobLauncher;
	private final Job job;

	@Autowired
	public App(final JobLauncher jobLauncher, final Job job) {
	    this.jobLauncher = jobLauncher;
	    this.job = job;
    }

	@Override
    public void run() {
        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Exit Status : " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exit Status : FUUUUUU");
        }
    }
}
