package lando.spring.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   OrderRepository orderRepository) {
        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee("Hazel", "Olvera", "Physicist")));
            log.info("Preloading " + employeeRepository.save(new Employee("Leo", "Infante", "Mathematician")));

            employeeRepository.findAll().forEach(employee ->
                    log.info("Preloaded " + employee));

            orderRepository.save(new Order("Mac Book Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> {
                log.info(("Preloaded " + order));
            });
        };
    }

}
