package de.renatius.poc.quarkus.library.entity

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "professor")
class Professor(
    @Id @Column(
        name = "uid",
        nullable = false,
        insertable = false,
        updatable = false
    ) @ColumnDefault("gen_random_uuid()") var uid: UUID? = null,

    @Column(name = "firstname", nullable = false) var firstname: String? = null,

    @Column(name = "middle_name") var middleName: String? = null,

    @Column(name = "surname", nullable = false) var lastname: String? = null,

    @ManyToMany(targetEntity = Course::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY) @JoinTable(
        name = "professor_holds_course",
        joinColumns = [JoinColumn(name = "professor_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")]
    ) var courses: Set<Course> = emptySet(),
)
