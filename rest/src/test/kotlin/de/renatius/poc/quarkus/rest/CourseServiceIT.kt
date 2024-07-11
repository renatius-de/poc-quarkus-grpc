package de.renatius.poc.quarkus.rest

import de.renatius.poc.quarkus.library.entity.Course
import de.renatius.poc.quarkus.library.entity.Professor
import de.renatius.poc.quarkus.library.entity.Student
import de.renatius.poc.quarkus.library.repository.CourseRepository
import de.renatius.poc.quarkus.library.repository.ProfessorRepository
import de.renatius.poc.quarkus.library.repository.StudentRepository
import de.renatius.poc.quarkus.rest.model.CourseModel
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import net.datafaker.Faker
import org.apache.commons.lang3.RandomStringUtils
import org.apache.http.HttpStatus.SC_CREATED
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_OK
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@QuarkusTest
class CourseServiceIT {
    companion object {
        private const val PATH = "/course"

        private val faker = Faker()
    }

    private val courseUid = UUID.randomUUID()
    private val professorUid = UUID.randomUUID()
    private val studentUid = UUID.randomUUID()

    @Inject
    lateinit var courseRepository: CourseRepository

    @Inject
    lateinit var professorRepository: ProfessorRepository

    @Inject
    lateinit var studentRepository: StudentRepository

    @AfterEach
    @Transactional
    fun tearDown() {
        courseRepository.deleteAll()
        professorRepository.deleteAll()
        studentRepository.deleteAll()
    }

    @Nested
    inner class GetIT {
        @Test
        fun `retrieve a given course`() {
            saveCourseWithProfessorAndStudent()

            val get = given()
                .`when`()
                .get("$PATH/$courseUid")

            val course = get
                .`as`(CourseModel::class.java)

            get
                .then()
                .statusCode(SC_OK)

            assertSoftly { softly ->
                softly.assertThat(course.uid!!).isEqualTo(courseUid)
                softly.assertThat(course.title).isNotBlank

                softly.assertThat(course.professors!!.first().uid!!).isEqualTo(professorUid)
                softly.assertThat(course.students!!.first().uid!!).isEqualTo(studentUid)
            }
        }

        @Test
        fun `retrieve a none existing given course`() {
            val uid = UUID.randomUUID()

            given()
                .`when`()
                .get("$PATH/$uid")
                .then()
                .statusCode(SC_NOT_FOUND)
        }

        @Test
        fun `count courses`() {
            val max = faker.random().nextInt(5, 10)
            for (i in 1..max) {
                saveCourse()
            }

            val get = given()
                .`when`()
                .get("$PATH/count")
            val count = get
                .`as`(Integer::class.java)

            get
                .then()
                .statusCode(SC_OK)

            assertThat(count).isEqualTo(max)
        }

        @Suppress("UNCHECKED_CAST")
        @Test
        fun `list all courses`() {
            val max = faker.random().nextInt(5, 10)
            val coursesEntities = mutableListOf<Course>()
            for (i in 1..max) {
                val course = saveCourse()
                coursesEntities.add(course)
            }
            val courses = coursesEntities
                .map { course ->
                    mapOf(
                        "professors" to emptyList<String>(),
                        "students" to emptyList<String>(),
                        "title" to course.title!!,
                        "uid" to course.uid.toString()
                    )
                }
                .toList()

            val get = given()
                .`when`()
                .get("$PATH/list")
            val list = get
                .`as`(List::class.java)

            get
                .then()
                .statusCode(SC_OK)

            assertThat(list).isEqualTo(courses)
        }
    }

    @Nested
    inner class PostIT {
        @Test
        fun `create a course with given uid`() {
            val uid = UUID.randomUUID()
            val title = RandomStringUtils.randomAlphanumeric(10, 1000)

            given()
                .contentType(APPLICATION_JSON)
                .body("{\"uid\":\"$uid\",\"title\":\"$title\"}")
                .`when`()
                .post(PATH)
                .then()
                .statusCode(SC_CREATED)
                .body("uid", notNullValue())
                .body("uid", `is`(not(uid)))
                .body("title", `is`(title))
                .body("professors", isA(Collection::class.java))
                .body("students", isA(Collection::class.java))
        }

        @Disabled("Broken right now.")
        @Test
        fun `create a course with professor and student`() {
            val title = RandomStringUtils.randomAlphanumeric(10, 1000)

            given()
                .contentType(APPLICATION_JSON)
                .body("{\"title\":\"$title\",\"professors\":[{\"uid\":\"$professorUid\"}]}")
                .`when`()
                .post(PATH)
                .then()
                .statusCode(SC_CREATED)
                .body("uid", notNullValue())
                .body("title", `is`(title))
                .body("professors", isA(Collection::class.java))
                .body("students", isA(Collection::class.java))
        }

        @Test
        fun `create a course`() {
            val title = RandomStringUtils.randomAlphanumeric(10, 1000)

            given()
                .contentType(APPLICATION_JSON)
                .body("{\"title\":\"$title\"}")
                .`when`()
                .post(PATH)
                .then()
                .statusCode(SC_CREATED)
                .body("uid", notNullValue())
                .body("title", `is`(title))
                .body("professors", isA(Collection::class.java))
                .body("students", isA(Collection::class.java))
        }
    }

    @Transactional
    fun saveCourse(): Course {
        val course = Course(title = faker.text().text(10, 1000))
        courseRepository.persistAndFlush(
            course
        )

        return course
    }

    @Transactional
    fun saveCourseWithProfessorAndStudent() {
        val professor = Professor(
            professorUid,
            faker.name().firstName(),
            faker.name().nameWithMiddle(),
            faker.name().lastName()
        )
        professorRepository.persistAndFlush(professor)
        val student = Student(
            studentUid,
            faker.name().firstName(),
            faker.name().nameWithMiddle(),
            faker.name().lastName(),
            faker.numerify("#######")
        )
        studentRepository.persistAndFlush(student)
        val course = Course(
            courseUid,
            faker.text().text(10, 1000),
            setOf(professor),
            setOf(student)
        )
        courseRepository.persistAndFlush(course)
    }
}
