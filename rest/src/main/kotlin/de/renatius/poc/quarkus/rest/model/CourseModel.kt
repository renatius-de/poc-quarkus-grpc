package de.renatius.poc.quarkus.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.renatius.poc.quarkus.library.entity.Course
import de.renatius.poc.quarkus.library.entity.Professor
import de.renatius.poc.quarkus.library.entity.Student
import java.util.*
import javax.validation.constraints.NotBlank

data class CourseModel(
    @JsonProperty("uid")
    var uid: UUID? = null,

    @JsonProperty("title", required = true)
    @NotBlank
    var title: String,

    @JsonIgnoreProperties("courses")
    @JsonProperty("professors")
    var professors: Set<ProfessorModel>? = emptySet(),

    @JsonIgnoreProperties("courses")
    @JsonProperty("students")
    var students: Set<StudentModel>? = emptySet(),
) {
    companion object {
        fun convertFromEntity(course: Course): CourseModel {
            val professors = course
                .professors
                .map { professor ->
                    ProfessorModel(
                        professor.uid,
                        professor.firstname!!,
                        professor.middleName,
                        professor.lastname!!
                    )
                }
                .toSet()
            val students = course
                .students
                .map { student ->
                    StudentModel(
                        student.uid,
                        student.firstname!!,
                        student.middleName,
                        student.lastname!!,
                        student.matriculationNumber
                    )
                }
                .toSet()

            return CourseModel(course.uid, course.title!!, professors, students)
        }
    }

    fun convertToEntity(): Course {
        uid = uid ?: UUID.randomUUID()
        val professorsEntities = professors
            ?.map { professor ->
                Professor(professor.uid!!, professor.firstname, professor.middleName, professor.lastname)
            }
            ?.toSet()
            ?: emptySet()
        val studentsEntities = students
            ?.map { student ->
                Student(
                    student.uid!!,
                    student.firstname,
                    student.middleName,
                    student.lastname,
                    student.matriculationNumber
                )
            }
            ?.toSet()
            ?: emptySet()

        return Course(uid!!, title, professorsEntities, studentsEntities)
    }
}
