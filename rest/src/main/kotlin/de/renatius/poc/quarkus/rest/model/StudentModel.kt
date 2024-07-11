package de.renatius.poc.quarkus.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.NotBlank

data class StudentModel(
    @JsonProperty("uid")
    var uid: UUID? = null,

    @JsonProperty("firstname")
    @NotBlank
    var firstname: String,

    @JsonProperty("middle_name")
    var middleName: String? = null,

    @JsonProperty("lastname")
    @NotBlank
    var lastname: String,

    @JsonProperty("matriculation_number")
    var matriculationNumber: String,

    @JsonIgnoreProperties("students")
    @JsonProperty("courses")
    var courses: Set<CourseModel>? = emptySet(),
)
