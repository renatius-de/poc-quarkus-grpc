package de.renatius.poc.quarkus.library.repository

import de.renatius.poc.quarkus.library.entity.Course
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class CourseRepository : PanacheRepositoryBase<Course, UUID>
