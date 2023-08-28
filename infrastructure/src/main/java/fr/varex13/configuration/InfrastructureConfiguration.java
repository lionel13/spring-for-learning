package fr.varex13.configuration;

import fr.varex13.student.StudentRepositoryPostgreSql;
import fr.varex13.student.ZazaRepository;
import fr.varex13.student.outputport.StudentRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
//@ComponentScan("fr.varex13")
@EnableJpaRepositories("fr.varex13")
public class InfrastructureConfiguration {

    @Bean
    public StudentRepository studentRepositoryPostgreSql(final ZazaRepository zazaRepository) {
        return new StudentRepositoryPostgreSql(zazaRepository);
    }
}
