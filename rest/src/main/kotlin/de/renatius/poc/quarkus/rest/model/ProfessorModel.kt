package de.renatius.poc.quarkus.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ProfessorModel(
    @JsonProperty("uid")
    var uid: UUID? = null,

    @JsonProperty("firstname", required = true)
    var firstname: String,

    @JsonProperty("middle_name")
    var middleName: String? = null,

    @JsonProperty("lastname", required = true)
    var lastname: String,

    @JsonIgnoreProperties("professors")
    @JsonProperty("courses")
    var courses: Set<CourseModel>? = emptySet(),
)
