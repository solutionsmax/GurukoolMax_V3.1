package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPopulateList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import retrofit2.http.*

interface FleetApi {

    @GET
    suspend fun retrieveBusRouteList(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetBusRouteList>

    @GET
    suspend fun retrieveBusStopList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetBusPickupPointsList>

    @GET
    suspend fun retrieveBusPickupSchedule(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iRouteID") iRouteID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<FleetPickupScheduleList>

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

    @GET
    suspend fun postFleetPhoto(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body fleetRegisterPostInfoItem: FleetRegisterPostInfoItem
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
}