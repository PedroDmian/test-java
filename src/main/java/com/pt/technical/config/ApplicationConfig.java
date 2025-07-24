package com.pt.technical.config;

import com.pt.technical.application.service.ProcessPaymentService;
import com.pt.technical.application.service.TaskService;
import com.pt.technical.application.usecase.*;
import com.pt.technical.domain.repository.DebitAccountRepository;
import com.pt.technical.domain.repository.LoanRepository;
import com.pt.technical.domain.repository.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskService taskService(TaskRepository taskRepository) { return new TaskService(taskRepository);}

    @Bean
    public ProcessPaymentService processPaymentService(LoanRepository loanRepository, DebitAccountRepository accountRepository) { return new ProcessPaymentService(loanRepository, accountRepository); }

    @Bean
    public ProcessLoanPaymentUseCase processLoanPaymentUseCase(ProcessPaymentService processPaymentService) { return processPaymentService; }

    @Bean
    public SaveTaskUseCase saveTaskUseCase(TaskService taskService) { return taskService; }
    @Bean
    public GetTaskUseCase getTaskUseCase(TaskService taskService) { return taskService; }
    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskService taskService) { return taskService; }
    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskService taskService) { return taskService; }
}
