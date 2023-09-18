package fr.varex13.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZazaRepository extends JpaRepository<StudentEntity, Long> {
}
