package de.renatius.poc.quarkus.rest.resource

import de.renatius.poc.quarkus.library.entity.Course
import de.renatius.poc.quarkus.library.repository.CourseRepository
import de.renatius.poc.quarkus.rest.model.CourseModel
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@ApplicationScoped
@OpenAPIDefinition(info = Info(title = "Example REST API", version = "1.0.0"))
@Path("/course")
open class CourseService {
    @Inject
    private lateinit var courseRepository: CourseRepository

    // GET
    @GET
    @APIResponses(
        APIResponse(responseCode = "200"),
        APIResponse(responseCode = "404")
    )
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    fun get(uid: UUID): Response {
        val course: Course = courseRepository.findById(uid)
            ?: return Response
                .status(Response.Status.NOT_FOUND)
                .build()

        return Response
            .status(Response.Status.OK)
            .entity(CourseModel.convertFromEntity(course))
            .build()
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    fun count(): Response {
        return Response
            .status(Response.Status.OK)
            .entity(courseRepository.count())
            .build()
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun list(): List<CourseModel> {
        return courseRepository
            .listAll()
            .map { courseEntity -> CourseModel.convertFromEntity(courseEntity) }
    }

    // POST
    @POST
    @APIResponses(
        APIResponse(responseCode = "201")
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun create(courseModel: CourseModel): Response {
        val course = courseModel.convertToEntity()
        courseRepository.persist(course)

        return Response
            .status(Response.Status.CREATED)
            .entity(CourseModel.convertFromEntity(course))
            .build()
    }

    // PUT
    @PUT
    @Path("/{uid}")
    @APIResponses(
        APIResponse(responseCode = "200"),
        APIResponse(responseCode = "404")
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun update(uid: UUID, courseModel: CourseModel): Response {
        val course: Course = courseRepository.findById(uid)
            ?: return Response
                .status(Response.Status.NOT_FOUND)
                .build()

        course.title = courseModel.title

        courseRepository.persist(course)

        return Response
            .status(Response.Status.OK)
            .entity(CourseModel.convertFromEntity(course))
            .build()
    }

    // DELETE
    @DELETE
    @APIResponses(
        APIResponse(responseCode = "202"),
        APIResponse(responseCode = "404")
    )
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun delete(uid: UUID): Response {
        if (courseRepository.deleteById(uid)) {
            return Response
                .status(Response.Status.ACCEPTED)
                .build()
        }

        return Response
            .status(Response.Status.NOT_FOUND)
            .build()
    }
}
