package com.solutionsmax.gurukoolmax_v3.core.common

object PortalIdConstants {
    const val PRINCIPAL_PORTAL = 1
    const val MANAGEMENT_PORTAL = 2
    const val FACULTY_PORTAL = 3
    const val STUDENT_PORTAL = 4
    const val PARENT_PORTAL = 5
    const val ALUMNI_PORTAL = 6
    const val INDUSTRY_PORTAL = 7
    const val SCHOOL_PORTAL = 8
    const val ASSESSMENTS = 9
    const val LEARNING_MOOC = 10
}

object PortalNameConstants {
    const val ADMINISTRATORS = "Administrators"
    const val MANAGEMENT = "Management"
    const val FACULTY = "Faculty"
    const val STUDENT = "Student"
    const val PARENT = "Parent"
    const val ALUMNI = "Alumni"
    const val OPERATIONS = "Operations"
}

object BaseURL {
    const val BASE_URL = "http://webservices.educatemax.com/"
}

object TokenConstants {
    private const val userName = "SolutionsMax"
    private const val password = "Web6serv!ce@ApI"
    private const val grantType = "password"
    const val BEARER = "bearer"

    const val tokenValue = "username=$userName&password=$password&grant_type=$grantType"
}

object RequestBodyConstants {
    const val REQUEST_BODY = "text/plain"
}

object PhotoConstants {
    const val MEDIA_BUS_IMAGE = "Media/Fleet/"
}

object ApiKey {
    const val MOVIE_API_KEY = "AIzaSyAQIEp3ZXGu1mKVBwc4moU3LGXXIg9XO44"
}