package de.renatius.poc.quarkus.library.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity
@Table(name = "professor")
class Professor(
    @Id
    @Column(
        name = "uid",
        nullable = false,
        insertable = false,
        updatable = false
    )
    @ColumnDefault("gen_random_uuid()")
    var uid: UUID? = null,

    @Column(name = "firstname", nullable = false)
    var firstname: String? = null,

    @Column(name = "middle_name")
    var middleName: String? = null,

    @Column(name = "surname", nullable = false)
    var lastname: String? = null,

    @ManyToMany(targetEntity = Course::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "professor_holds_course",
        joinColumns = [JoinColumn(name = "professor_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")]
    )
    var courses: Set<Course> = emptySet(),
)
