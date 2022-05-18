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

    // SchoolMax Master
    const val POPULATE_SCHOOL_MAX_MASTER = "/SchoolMaxMaster/PopulateList"

    // Settings
    const val FETCH_SEMESTER_FORMAT_INFO = "/SchoolConfiguration/FetchIsSemesterFormatInfo"

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

    // Class
    const val POPULATE_CLASS_SEMESTER_FORMAT_2 = "/AClassRegistration/PopulateList"
    const val POPULATE_CLASS_SEMESTER_FORMAT_1 = "/AClassRoom/PopulateList"

    // CCE Scholastic
    const val POST_SCHOLASTIC_GRADING = "/ScholasticGrading/PostInfo"
    const val AMEND_SCHOLASTIC_GRADING = "/ScholasticGrading/AmendInfo"
    const val CHECK_DUPLICATE_ALL_SCHOLASTIC_GRADING = "/ScholasticGrading/CheckDuplicateByAll"
    const val CHECK_DUPLICATE_GRADE_SCHOLASTIC_GRADING = "/ScholasticGrading/CheckDuplicateGrade"
    const val CHECK_DUPLICATE_MAX_SCORE_RANGE_SCHOLASTIC_GRADING =
        "/ScholasticGrading/CheckDuplicateGradeByMaxScoreRange"
    const val CHECK_DUPLICATE_MIN_SCORE_RANGE_SCHOLASTIC_GRADING =
        "/ScholasticGrading/CheckDuplicateGradeByMinScoreRange"
    const val CHECK_DUPLICATE_GRADE_POINT_SCHOLASTIC_GRADING =
        "/ScholasticGrading/CheckDuplicateGradePoint"
    const val REMOVE_GRADE_SCHOLASTIC_GRADING = "/ScholasticGrading/RemoveGrade"
    const val SET_STATUS_SCHOLASTIC_GRADING = "/ScholasticGrading/SetStatusInfo"
    const val RETRIEVE_DETAILS_SCHOLASTIC_GRADING = "/ScholasticGrading/RetrieveDetails"
    const val RETRIEVE_LIST_SCHOLASTIC_GRADING = "/ScholasticGrading/RetrieveList"
    const val RETRIEVE_LIST_BY_GRADE_SCHOLASTIC_GRADING = "/ScholasticGrading/RetrieveGradeByList"

    // Scholastic
    const val POST_SCHOLASTIC_INFO = "/Scholastic/PostInfo"
    const val AMEND_SCHOLASTIC_INFO = "/Scholastic/AmendInfo"
    const val CHECK_DUPLICATE_SCHOLASTIC_INFO = "/Scholastic/CheckDuplicateInfo"
    const val SET_STATUS_SCHOLASTIC_INFO = "/Scholastic/SetStatusInfo"
    const val POPULATE_SCHOLASTIC_STUDENT_LIST = "/Scholastic/PopulateStudentList"
    const val RETRIEVE_SCHOLASTIC_DETAILS = "/Scholastic/RetrieveDetails"
    const val RETRIEVE_SCHOLASTIC_LIST = "/Scholastic/RetrieveList"
    const val SCHOLASTIC_CATEGORY_TYPES = "/Scholastic/ScholasticCategoryTypes"

    // Academic Project
    const val POST_PROJECT_INFO = "/ProjectRepository/PostInfo"
    const val AMEND_PROJECT_INFO = "/ProjectRepository/AmendInfo"
    const val AMEND_PROJECT_GUIDE_INFO = "/ProjectRepository/AmendProjectGuideInfo"
    const val SET_PROJECT_STATUS_INFO = "/ProjectRepository/SetStatusInfo"
    const val CHECK_DUPLICATE_PROJECT_INFO = "/ProjectRepository/CheckDuplicationInfo"
    const val RETRIEVE_DETAILS_PROJECT_INFO = "/ProjectRepository/RetrieveDetails"
    const val RETRIEVE_LIST_PROJECT_INFO = "/ProjectRepository/RetrieveList"
    const val SEARCH_PROJECT_INFO = "/ProjectRepository/RetrieveSearchResults"

    // Knowledge Management
    const val POST_KM_INFO = "/KM/PostInfo"
    const val AMEND_KM_INFO = "/KM/AmendInfo"
    const val SET_STATUS_KM_INFO = "/KM/SetStatusInfo"
    const val POST_KM_FILE_NAME_INFO = "/KM/PostFileName"
    const val REMOVE_KM_INFO = "/KM/RemoveKM"
    const val RETRIEVE_KM_DETAILS = "/KM/RetrieveDetails"
    const val RETRIEVE_KM_LIST = "/KM/RetrieveList"
    const val RETRIEVE_KM_ADVANCE_SEARCH = "/KM/RetrieveListByAdvancedSearchKeyword"
    const val RETRIEVE_KM_SEARCH = "/KM/RetrieveListBySearchKeyword"

    // Subject Management
    const val POST_SUBJECT_MANAGEMENT_INFO = "/ASubjectManagement/PostInfo"
    const val AMEND_SUBJECT_MANAGEMENT_INFO = "/ASubjectManagement/AmendInfo"
    const val CHECK_DUPLICATE_SUBJECT_MANAGEMENT_INFO = "/ASubjectManagement/CheckDuplicateSubject"
    const val FETCH_BOARD_ID_BY_CLASS_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/FetchBoardIDByClass"
    const val FETCH_SUBJECT_NAME_SUBJECT_MANAGEMENT_INFO = "/ASubjectManagement/FetchSubjectName"
    const val REMOVE_SUBJECT_SUBJECT_MANAGEMENT_INFO = "/ASubjectManagement/RemoveSubject"
    const val SET_ACTIVATION_STATUS_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/SetActivationStatus"
    const val POPULATE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/PopulateSubjectList"
    const val RETRIEVE_SUBJECT_LIST_BY_BOARD_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/PopulateSubjectListByBoard"
    const val POPULATE_SUBJECT_LIST_BY_FACULTY_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/PopulateSubjectListByFaculty"
    const val POPULATE_SUBJECT_LIST_BY_STUDENT_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/PopulateSubjectListByStudent"
    const val RETRIEVE_MY_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveMySubjectList"
    const val RETRIEVE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectList"
    const val RETRIEVE_SUBJECT_LIST_BY_STUDENT_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectListByStudent"
    const val RETRIEVE_SUBJECT_LIST_MAP_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectListMap"
    const val RETRIEVE_SUBJECT_LIST_MAP_BY_CLASS_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectListMapByClass"
    const val RETRIEVE_SUBJECT_LIST_MAP_BY_CLASS_BOARD_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectListMapByClassAndBoard"
    const val RETRIEVE_SUBJECT_LIST_MAP_MAP_DETAILS_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrieveSubjectListMapDetails"
    const val RETRIEVE_SUBJECT_PAGINATION_LIST_SUBJECT_MANAGEMENT_INFO =
        "/ASubjectManagement/RetrievePaginationList"

    // Subject Program
    const val POPULATE_SUBJECT_PROGRAM_LIST = "/ASubjectsPrograms/PopulateList"

    // Curriculum
    const val POST_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/PostInfo"
    const val AMEND_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/AmendInfo"
    const val CHECK_DUPLICATE_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/CheckDuplicateSyllabusEntry"
    const val FETCH_NEXT_SORT_ORDER_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/FetchNextSortOrder"
    const val FETCH_SESSION_TOPIC_NAME_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/FetchSessionTopicName"
    const val FETCH_SUBJECT_NAME_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/FetchSubjectName"
    const val FETCH_SYLLABUS_DETAILS_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/FetchSyllabusDetails"
    const val SET_STATUS_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/SetStatusInfo"
    const val POPULATE_LIST_BY_CLASS_REFERENCE_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/PopulateListByClassReference"
    const val POPULATE_SESSION_TOPIC_LIST_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/PopulateSessionTopicList"
    const val POPULATE_SUBJECT_LIST_BY_CLASS_REF_CURRICULUM_SETUP_INFO =
        "/ACurriculumSetup/PopulateSubjectListByClassReference"
    const val RETRIEVE_LIST_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/RetrieveList"
    const val RETRIEVE_DETAILS_CURRICULUM_SETUP_INFO = "/ACurriculumSetup/RetrieveDetails"

    // Examination Schedule
    const val POST_EXAM_SCHEDULE = "/AExaminationSchedule/PostInfo"
    const val AMEND_EXAM_SCHEDULE = "/AExaminationSchedule/AmendInfo"
    const val SET_STATUS_EXAM_SCHEDULE = "/AExaminationSchedule/SetStatusInfo"
    const val CHECK_DUPLICATE_EXAM_SCHEDULE = "/AExaminationSchedule/CheckDuplicateInfo"
    const val POPULATE_LIST_EXAM_SCHEDULE = "/AExaminationSchedule/PopulateList"
    const val POPULATE_LIST_FILTER_EXAM_SCHEDULE = "/AExaminationSchedule/PopulateListFilter"
    const val POPULATE_BY_STUDENT_NAME_EXAM_SCHEDULE =
        "/AExaminationSchedule/PopulateScheduleNameListByStudent"
    const val RETRIEVE_DETAILS_EXAM_SCHEDULE = "/AExaminationSchedule/RetrieveDetails"
    const val RETRIEVE_LIST_BY_STUDENT_EXAM_SCHEDULE =
        "/AExaminationSchedule/RetrieveExaminationListByStudent"
    const val RETRIEVE_LIST_EXAM_SCHEDULE = "/AExaminationSchedule/RetrieveExaminationList"

    // Examination Setup
    const val POST_EXAM_SETUP = "/ExaminationConfig/PostInfo"
    const val AMEND_EXAM_SETUP = "/ExaminationConfig/AmendInfo"
    const val APPROVAL_PROCESS_EXAM_SETUP = "/ExaminationConfig/ApprovalProcess"
    const val APPROVED_BY_PROCESS_EXAM_SETUP = "/ExaminationConfig/ApprovedBy"
    const val CHECK_DUPLICATE_EXAM_SETUP = "/ExaminationConfig/CheckDuplicateInfo"
    const val FETCH_START_END_TIME_EXAM_SETUP = "/ExaminationConfig/FecthStartAndEndTime"
    const val SET_STATUS_EXAM_SETUP = "/ExaminationConfig/SetStatusInfo"
    const val FETCH_COUNT_BY_CATEGORY_EXAM_SETUP = "/ExaminationConfig/FetchCountByCategory"
    const val FETCH_EXAMINATION_ID_EXAM_SETUP = "/ExaminationConfig/FetchExaminationID"
    const val FETCH_EXAMINATION_LIST_COUNT_EXAM_SETUP =
        "/ExaminationConfig/FetchExaminationListCount"
    const val REMOVE_EXAM_SETUP = "/ExaminationConfig/RemoveExam"
    const val POPULATE_SUBJECT_LIST_EXAM_SETUP = "/ExaminationConfig/PopulateSubjectList"
    const val POPULATE_SUBJECT_BY_CLASS_BOARD_LIST_EXAM_SETUP =
        "/ExaminationConfig/PopulateSubjectsByClassAndBoard"
    const val RETRIEVE_DETAILS_EXAM_SETUP = "/ExaminationConfig/RetrieveDetails"
    const val RETRIEVE_LIST_EXAM_SETUP = "/ExaminationConfig/RetrieveList"
    const val RETRIEVE_LIST_BY_FACULTY_EXAM_SETUP = "/ExaminationConfig/RetrieveListByFaculty"
    const val RETRIEVE_LIST_BY_STUDENT_EXAM_SETUP = "/ExaminationConfig/RetrieveListByStudent"
    const val RETRIEVE_STUDENT_EXAM_SUMMARY_EXAM_SETUP =
        "/ExaminationConfig/RetrieveStudentExaminationSummary"
    const val RETRIEVE_SUBJECT_LIST_EXAM_SETUP = "/ExaminationConfig/RetrieveSubjectList"
}