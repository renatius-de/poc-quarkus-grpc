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
@Table(name = "course")
class Course(
    @Id
    @Column(name = "uid", nullable = false, insertable = false, updatable = false)
    @ColumnDefault("gen_random_uuid()")
    var uid: UUID? = null,

    @Column(name = "title", nullable = false, unique = true, length = 1023)
    var title: String? = null,

    @ManyToMany(targetEntity = Professor::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "professor_holds_course",
        joinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "professor_uid", referencedColumnName = "uid")]
    )
    var professors: Set<Professor> = emptySet(),

    @ManyToMany(targetEntity = Student::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_takes_part_in_course",
        joinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "student_uid", referencedColumnName = "uid")]
    )
    var students: Set<Student> = emptySet(),
)
