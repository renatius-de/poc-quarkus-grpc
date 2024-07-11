package de.renatius.poc.quarkus.library.repository

import de.renatius.poc.quarkus.library.entity.Student
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class StudentRepository : PanacheRepositoryBase<Student, UUID>
