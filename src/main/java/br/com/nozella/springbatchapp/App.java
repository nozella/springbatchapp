package br.com.nozella.springbatchapp;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App extends Thread {
	
	public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("job-report.xml");
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
