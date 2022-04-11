package com.solutionsmax.gurukoolmax_v3.core.common

object MethodConstants {
    // Fleet Bus Route
    const val POST_FLEET_BUS_ROUTE = "/FleetBusRoute/PostInfo"
    const val AMEND_FLEET_BUS_ROUTE = "/FleetBusRoute/AmendInfo"
    const val SET_FLEET_BUS_ROUTE_STATUS = "/FleetBusRoute/SetStatusInfo"
    const val FETCH_FLEET_BUS_ROUTE_NAME = "/FleetBusRoute/FetchRouteName"
    const val CHECK_DUPLICATE_FLEET_BUS_ROUTE = "/FleetBusRoute/CheckDuplicateRoute"
    const val POPULATE_FLEET_BUS_ROUTE_NAME = "/FleetBusRoute/PopulateList"
    const val RETRIEVE_FLEET_ROUTE_DETAILS = "/FleetBusRoute/RetrieveDetails"
    const val RETRIEVE_FLEET_BUS_ROUTES = "/FleetBusRoute/RetrieveList"
    const val RETRIEVE_FLEET_RETRIEVE_STUDENT_RESERVATION_LIST =
        "/FleetBusRoute/RetrieveStudentReservationList"

    // Fleet Bus Stop
    const val POST_FLEET_BUS_STOP = "/FleetBusStop/PostInfo"
    const val AMEND_FLEET_BUS_STOP = "/FleetBusStop/AmendInfo"
    const val SET_FLEET_BUS_STOP_STOP = "/FleetBusStop/SetStatusInfo"
    const val CHECK_DUPLICATE_FLEET_BUS_STOP = "/FleetBusStop/CheckDuplicateStop"
    const val POPULATE_FLEET_BUS_STOP = "/FleetBusStop/PopulateList"
    const val RETRIEVE_FLEET_BUS_STOPS_DETAILS = "/FleetBusStop/RetrieveDetails"
    const val RETRIEVE_FLEET_BUS_STOPS = "/FleetBusStop/RetrieveList"

    // Fleet Bus Schedule
    const val POST_FLEET_BUS_SCHEDULE = "/FleetBusSchedule/PostInfo"
    const val AMEND_FLEET_BUS_SCHEDULE = "/FleetBusSchedule/AmendInfo"
    const val SET_STATUS_FLEET_BUS_SCHEDULE = "/FleetBusSchedule/SetStatusInfo"
    const val CHECK_DUPLICATE_FLEET_BUS_SCHEDULE = "/FleetBusSchedule/CheckDuplicate"
    const val RETRIEVE_DETAILS_FLEET_BUS_SCHEDULE = "/FleetBusSchedule/RetrieveDetails"
    const val RETRIEVE_BUS_SCHEDULE_BY_ROUTE_FLEET_BUS_SCHEDULE =
        "/FleetBusSchedule/RetrieveBusScheduleByRoute"
    const val RETRIEVE_BUS_STOP_BY_ROUTE_FLEET_BUS_SCHEDULE =
        "/FleetBusSchedule/RetrieveBusStopByRoute"
    const val RETRIEVE_STUDENT_BUS_ROUTE_FLEET_BUS_SCHEDULE =
        "/FleetBusSchedule/RetrieveStudentBusRoute"
    const val RETRIEVE_FLEET_PICKUP_SCHEDULE = "/FleetBusSchedule/RetrieveList"

    const val UPLOAD_PHOTO = "/Upload/PostInfo"

    // Fleet Registration
    const val FLEET_REGISTRATION_POST_INFO = "/FleetRegistration/PostInfo"
    const val FLEET_REGISTRATION_AMEND_INFO = "/FleetRegistration/AmendInfo"
    const val FLEET_REGISTRATION_CHECK_DUPLICATE = "/FleetRegistration/CheckDuplicateInfo"
    const val FLEET_REGISTRATION_FETCH_PHOTO_NAME = "/FleetRegistration/FetchPhotoName"
    const val FLEET_REGISTRATION_POST_PHOTO = "/FleetRegistration/PostFleetPhoto"
    const val FLEET_REGISTRATION_RETRIEVE_DETAILS = "/FleetRegistration/RetrieveDetails"
    const val FLEET_REGISTRATION_RETRIEVE_LIST = "/FleetRegistration/RetrieveList"
    const val FLEET_REGISTRATION_POPULATE_LIST = "/FleetRegistration/PopulateList"
    const val STUDENT_CHECK_VALID_ADMISSION_NUMBER = "/Student/CheckValidAdmissionNum"

    // Fleet Student Attendance
    const val FLEET_STUDENT_ATTENDANCE_POST_INFO = "/FleetStudentAttendance/PostInfo"
    const val FLEET_STUDENT_ATTENDANCE_POST_MANUALLY =
        "/FleetStudentAttendance/PostAttendanceManually"
    const val FLEET_STUDENT_ATTENDANCE_AMEND_INFO = "/FleetStudentAttendance/AmendInfo"
    const val FLEET_STUDENT_ATTENDANCE_CHECK_DUPLICATE =
        "/FleetStudentAttendance/CheckDuplicateInfo"
    const val FLEET_STUDENT_ATTENDANCE_CHECK_ATTENDANCE =
        "/FleetStudentAttendance/CheckStudentAttendance"
    const val FLEET_STUDENT_ATTENDANCE_CHECK_DUPLICATE_BY_ADMISSION_NUMBER =
        "/FleetStudentAttendance/CheckDuplicateByAdmissionNumInfo"
    const val FLEET_STUDENT_ATTENDANCE_RETRIEVE_DETAILS = "/FleetStudentAttendance/RetrieveDetails"
    const val FLEET_STUDENT_ATTENDANCE_RETRIEVE_LIST = "/FleetStudentAttendance/RetrieveList"

    // Fleet Movement
    const val FLEET_MOVEMENT_POST_INFO = "/FleetMovement/PostInfo"
    const val FLEET_MOVEMENT_AMEND_INFO = "/FleetMovement/AmendInfo"
    const val FLEET_MOVEMENT_CHECK_DUPLICATE_INFO = "/FleetMovement/CheckDuplicate"
    const val FLEET_MOVEMENT_FETCH_WORKFLOW_STATUS = "/FleetMovement/FetchWorkflowStatus"
    const val FLEET_MOVEMENT_FETCH_CLOSING_READING = "/FleetMovement/FetchClosingReading"
    const val FLEET_MOVEMENT_RETRIEVE_DETAILS = "/FleetMovement/RetrieveDetails"
    const val FLEET_MOVEMENT_RETRIEVE_LIST = "/FleetMovement/RetrieveList"
    const val FLEET_MOVEMENT_POPULATE_LIST = "/FleetMovement/PopulateList"
    const val FLEET_MOVEMENT_SET_STATUS = "/FleetMovement/SetStatusInfo"

    // Fleet Fuel Log
    const val FLEET_FUEL_LOG_POST_INFO = "/FleetFuelLogRegistration/PostInfo"
    const val FLEET_FUEL_LOG_AMEND_INFO = "/FleetFuelLogRegistration/AmendInfo"
    const val FLEET_FUEL_LOG_CHECK_DUPLICATE_INFO = "/FleetFuelLogRegistration/CheckDuplicateInfo"
    const val FLEET_FUEL_LOG_CHECK_RETRIEVE_LIST = "/FleetFuelLogRegistration/RetrieveList"
    const val FLEET_FUEL_LOG_CHECK_RETRIEVE_DETAILS = "/FleetFuelLogRegistration/RetrieveDetails"
    const val FLEET_FUEL_LOG_CHECK_SET_STATUS = "/FleetFuelLogRegistration/SetStatusInfo"

    // Master
    const val POST_MASTER_INFO = "/Master/PostInfo"
    const val AMEND_MASTER_INFO = "/Master/AmendInfo"
    const val CHECK_DUPLICATE_MASTER_INFO = "/Master/CheckDuplicateInfo"
    const val FETCH_MASTER_DESCRIPTION = "/Master/FetchDescription"
    const val SET_MASTER_STATUS = "/Master/SetStatusInfo"
    const val MASTER_POPULATE_INFO_BY_PARENT = "/Master/PopulateInfoByParent"
    const val POPULATE_MASTER_LIST = "/Master/PopulateList"

    // Fleet Bus Routes
    const val FLEET_POPULATE_BUS_ROUTES = "/FleetBusRoute/PopulateList"

    // Error Logs
    const val POST_ERROR_LOGS = "/LogError/PostInfo"

    // Management Login
    const val MANAGEMENT_LOGIN = "/StaffRegistration/ValidateManagementLogin"

    // GPS Tracker
    const val POST_FLEET_GPS = "/FleetGPS/PostInfo"
    const val AMEND_FLEET_GPS = "/FleetGPS/AmendInfo"
    const val CHECK_DUPLICATE_FLEET_GPS = "/FleetGPS/CheckDuplicateGeoCoordinates"
    const val RETRIEVE_FLEET_GPS_DETAILS = "/FleetGPS/RetrieveDetails"
    const val RETRIEVE_FLEET_GPS_LIST = "/FleetGPS/RetrieveList"
    const val RETRIEVE_FLEET_GPS_TOP_LIST = "/FleetGPS/RetrieveTopList"
    const val RETRIEVE_FLEET_GPS_BY_DATE_RANGE = "/FleetGPS/RetrieveListByDateRange"
}