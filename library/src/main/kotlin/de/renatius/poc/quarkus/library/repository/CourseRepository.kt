package de.renatius.poc.quarkus.library.repository

import de.renatius.poc.quarkus.library.entity.Course
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CourseRepository : PanacheRepositoryBase<Course, UUID>
