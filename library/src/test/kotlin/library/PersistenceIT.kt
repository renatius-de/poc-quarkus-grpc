package library

import de.renatius.poc.quarkus.library.entity.Course
import de.renatius.poc.quarkus.library.entity.Professor
import de.renatius.poc.quarkus.library.entity.Student
import de.renatius.poc.quarkus.library.repository.CourseRepository
import de.renatius.poc.quarkus.library.repository.ProfessorRepository
import de.renatius.poc.quarkus.library.repository.StudentRepository
import io.quarkus.test.TestTransaction
import io.quarkus.test.junit.QuarkusTest
import net.datafaker.Faker
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test
import java.util.*
import javax.inject.Inject

@QuarkusTest
@TestTransaction
class PersistenceIT {
    companion object {
        private val faker = Faker()
    }

    @Inject
    lateinit var courseRepository: CourseRepository

    @Inject
    lateinit var professorRepository: ProfessorRepository

    @Inject
    lateinit var studentRepository: StudentRepository

    @Test
    fun `list all courses`() {
        val professors = setOf(
            Professor(
                UUID.randomUUID(), faker.name().firstName(), faker.name().nameWithMiddle(), faker.name().lastName()
            ), Professor(
                UUID.randomUUID(), faker.name().firstName(), faker.name().nameWithMiddle(), faker.name().lastName()
            ), Professor(
                UUID.randomUUID(), faker.name().firstName(), faker.name().nameWithMiddle(), faker.name().lastName()
            )
        )
        professorRepository.persist(professors)

        val students = setOf(
            Student(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().nameWithMiddle(),
                faker.name().lastName(),
                faker.numerify("#######")
            ), Student(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().nameWithMiddle(),
                faker.name().lastName(),
                faker.numerify("#######")
            ), Student(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().nameWithMiddle(),
                faker.name().lastName(),
                faker.numerify("#######")
            )
        )
        studentRepository.persist(students)

        val course = Course(UUID.randomUUID(), faker.text().text(10, 1000), professors, students)
        courseRepository.persist(course)

        val actualCourses = courseRepository.listAll()

        assertSoftly { softly ->
            softly.assertThat(actualCourses).isEqualTo(listOf(course))
            softly.assertThat(actualCourses[0].professors).isEqualTo(professors)
            softly.assertThat(actualCourses[0].students).isEqualTo(students)
        }
    }

    @Test
    fun `list all professors`() {
        val courses = setOf(
            Course(UUID.randomUUID(), faker.text().text(10, 1000)),
            Course(UUID.randomUUID(), faker.text().text(10, 1000)),
            Course(UUID.randomUUID(), faker.text().text(10, 1000))
        )
        courseRepository.persist(courses)

        val professor = Professor(
            UUID.randomUUID(), faker.name().firstName(), faker.name().nameWithMiddle(), faker.name().lastName(), courses
        )
        professorRepository.persist(professor)

        val actualProfessors = professorRepository.listAll()

        assertSoftly { softly ->
            softly.assertThat(actualProfessors).isEqualTo(listOf(professor))
            softly.assertThat(actualProfessors[0].courses).isEqualTo(courses)
        }
    }

    @Test
    fun `list all students`() {
        val courses = setOf(
            Course(UUID.randomUUID(), faker.text().text(10, 1000)),
            Course(UUID.randomUUID(), faker.text().text(10, 1000)),
            Course(UUID.randomUUID(), faker.text().text(10, 1000))
        )
        courseRepository.persist(courses)

        val student = Student(
            UUID.randomUUID(),
            faker.name().firstName(),
            faker.name().nameWithMiddle(),
            faker.name().lastName(),
            faker.numerify("#######"),
            courses
        )
        studentRepository.persist(student)

        val students = studentRepository.listAll()

        assertSoftly { softly ->
            softly.assertThat(students).isEqualTo(listOf(student))
            softly.assertThat(students[0].courses).isEqualTo(courses)
        }
    }
}
