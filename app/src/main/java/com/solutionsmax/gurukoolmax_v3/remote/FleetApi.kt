package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.PopulateFleetBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PostBusRouteItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.RetrieveStudentReservationBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_schedule.PostBusScheduleItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PostFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSPostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPopulateList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetPostPhotoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface FleetApi {

    @POST
    suspend fun postBusRouteInfo(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Body postBusRouteItem: PostBusRouteItem
    ): Int

    @POST
    suspend fun amendBusRouteInfo(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Body postBusRouteItem: PostBusRouteItem
    ): Int

    @GET
    suspend fun setBusRouteStatus(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun fetchBusRouteName(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun checkDuplicateBusRouteName(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("sRouteName") sRouteName: String
    ): Int

    @GET
    suspend fun populateBusRouteName(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): MutableList<PopulateBusRoutesItems>

    @GET
    suspend fun retrieveBusRouteDetails(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetBusRouteList>

    @GET
    suspend fun retrieveStudentReservationList(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iPickupRouteID") iPickupRouteID: Int,
        @Query("iDropRouteID") iDropRouteID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveStudentReservationBusRoutesItems>

    @GET
    suspend fun retrieveBusRouteList(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetBusRouteList>

    /**
     * Bus Stop
     */

    @POST
    suspend fun postFleetBusStops(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postFleetBusStopItems: PostFleetBusStopItems
    ): Int

    @POST
    suspend fun amendFleetBusStops(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postFleetBusStopItems: PostFleetBusStopItems
    ): Int

    @GET
    suspend fun setFleetBusStopStatus(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun checkDuplicateFleetBusStop(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("sStopName") sStopName: String
    ): Int

    @GET
    suspend fun populateFleetBusStopList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iEditMode") iEditMode: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<PopulateFleetBusStopItems>

    @GET
    suspend fun retrieveBusStopDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetBusPickupPointsList>

    @GET
    suspend fun retrieveBusStopList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetBusPickupPointsList>

    /**
     * Bus Schedule
     */
    @POST
    suspend fun postBusSchedule(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postBusScheduleItem: PostBusScheduleItem
    ): Int

    @POST
    suspend fun amendBusSchedule(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postBusScheduleItem: PostBusScheduleItem
    ): Int


    @GET
    suspend fun checkDuplicateBusSchedule(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iStopID") iStopID: Int
    ): Int

    @GET
    suspend fun retrieveBusPickupScheduleDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetPickupScheduleList>

    @GET
    suspend fun retrieveBusPickupSchedule(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetPickupScheduleList>

    @GET
    suspend fun retrieveStudentBusRoute(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iSortID") iSortID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetPickupScheduleList>

    @GET
    suspend fun populateFleetBusRoutes(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): MutableList<PopulateFleetBusRoutesItems>

    // Fleet Registration
    @POST
    suspend fun postRegisterFleetInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetRegisterPostInfoItem: FleetRegisterPostInfoItem
    ): Int

    @POST
    suspend fun amendRegisterFleetInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetRegisterPostInfoItem: FleetRegisterPostInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateFleetRegistration(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("sRegistrationNumber") sRegistrationNumber: String
    ): Int

    @Multipart
    @POST
    suspend fun uploadPhoto(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Part file: MultipartBody.Part,
        @PartMap content: Map<String, @JvmSuppressWildcards RequestBody>
    ): ResponseBody

    @POST
    suspend fun postFleetPhoto(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetPostPhotoItem: FleetPostPhotoItem
    ): Int

    @GET
    suspend fun retrieveRegisteredFleetList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): List<FleetRegisterRetrieveListItem>

    @GET
    suspend fun retrieveRegisteredFleetDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetRegisterRetrieveListItem>

    @GET
    suspend fun fetchRegisteredFleetPhoto(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iVehicleID") iVehicleID: Int
    ): String

    @GET
    suspend fun populateRegisteredFleetList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): MutableList<PopulateRegisteredFleetList>

    @GET
    suspend fun setRegisteredFleetStatus(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    // Fleet Movement
    @POST
    suspend fun postFleetMovementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetMovementPostInfoItem: FleetMovementPostInfoItem
    ): Int

    @POST
    suspend fun amendFleetMovementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetMovementPostInfoItem: FleetMovementPostInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateFleetMovementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iVehicleID") iVehicleID: Int,
        @Query("iOpeningReading") iOpeningReading: Int,
        @Query("dMovementDate") dMovementDate: String
    ): Int

    @GET
    suspend fun fetchWorkflowStatusFleetMovement(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iVehicleID") iVehicleID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): Int


    @GET
    suspend fun fetchClosingRangeFleetMovement(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iVehicleID") iVehicleID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): Int

    @GET
    suspend fun retrieveFleetMovementDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetMovementRetrieveItem>

    @GET
    suspend fun retrieveFleetMovementList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): List<FleetMovementRetrieveItem>

    @GET
    suspend fun populateFleetMovementList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): List<FleetMovementPopulateList>

    @GET
    suspend fun setFleetMovementStatus(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    /**
     * Fuel Logs
     */
    @POST
    suspend fun postFuelLog(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fuelLogsPostInfoItem: FuelLogsPostInfoItem
    ): Int

    @POST
    suspend fun amendFuelLog(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fuelLogsPostInfoItem: FuelLogsPostInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateFuelLog(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iFleetID") iFleetID: Int,
        @Query("iOdometerID") iOdometerID: Int
    ): Int

    @GET
    suspend fun retrieveFuelLogsDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FuelLogsRetrieveItems>

    @GET
    suspend fun retrieveFuelLogsList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iFleetID") iFleetID: Int,
        @Query("iWorkflowStatusID") iWorkflowStatusID: Int
    ): List<FuelLogsRetrieveItems>

    @GET
    suspend fun setFuelLogsStatus(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    /**
     * Student OnBoard Attendance
     */
    @GET
    suspend fun checkValidAdmissionNumber(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("sAdmissionNum") sAdmissionNum: String
    ): Int

    @GET
    suspend fun checkStudentAttendance(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStudentID") iStudentID: Int,
        @Query("sAdmissionNum") sAdmissionNum: String,
        @Query("iRouteID") iRouteID: Int,
        @Query("dDateOfTravel") dDateOfTravel: String
    ): Int

    @GET
    suspend fun checkDuplicateAttendance(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStudentID") iStudentID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("dDateOfTravel") dDateOfTravel: String
    ): Int

    @GET
    suspend fun checkDuplicateAttendanceByAdmissionNumber(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("sAdmissionNumber") sAdmissionNumber: String,
        @Query("iRouteID") iRouteID: Int,
        @Query("dDateOfTravel") dDateOfTravel: String
    ): Int

    @GET
    suspend fun postFleetStudentAttendance(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body onBoardAttendancePostItem: OnBoardAttendancePostItem
    ): Int

    @GET
    suspend fun amendFleetStudentAttendance(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body onBoardAttendancePostItem: OnBoardAttendancePostItem
    ): Int

    @POST
    suspend fun postFleetStudentAttendanceManually(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStudentID") iStudentID: Int,
        @Query("sAdmissionNum") sAdmissionNum: String,
        @Query("iRouteID") iRouteID: Int,
        @Query("dDateOfTravel") dDateOfTravel: String,
        @Query("iAttendanceStatusID") niAttendanceStatusID: Int
    ): Int

    /**
     * Fleet GPS
     */
    @POST
    suspend fun postFleetGPS(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetGPSPostItem: FleetGPSPostItem
    ): Int

    @POST
    suspend fun amendFleetGPS(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetGPSPostItem: FleetGPSPostItem
    ): Int

    @GET
    suspend fun checkDuplicateFleetGpsCoordinates(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("sAddress") sAddress: String,
        @Query("sLongitude") sLongitude: String,
        @Query("sLatitude") sLatitude: String,
        @Query("sSyncDT") sSyncDT: String,
        @Query("iRouteID") iRouteID: Int,
        @Query("iDriverID") iDriverID: Int
    ): Int

    @GET
    suspend fun retrieveFleetGPSDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<FleetGPSRetrieveItems>

    @GET
    suspend fun retrieveFleetGPSList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iDriverID") iDriverID: Int
    ): List<FleetGPSRetrieveItems>

    @GET
    suspend fun retrieveFleetGPSTopList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iDriverID") iDriverID: Int
    ): List<FleetGPSRetrieveItems>

    @GET
    suspend fun retrieveFleetGPSByDateRangeList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iRouteID") iRouteID: Int,
        @Query("iDriverID") iDriverID: Int,
        @Query("dFromDate") dFromDate: String,
        @Query("dToDate") dToDate: String
    ): List<FleetGPSRetrieveItems>
}