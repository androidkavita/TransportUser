package com.govahan.com.data

import com.govahan.com.activities.ReferNEarnResponse
import com.govahan.com.activities.TransactionReportResponse
import com.govahan.com.activities.loadercompletedbookingdetails.UploadDocumentsResponse
import com.govahan.com.model.*
import com.govahan.com.model.addfavouritelocation.AddFavouriteLocationModel
import com.govahan.com.model.authorisedFranchisesDisttListModel.AuthorisedFranchisesDisttListResponseModel
import com.govahan.com.model.authorisedFranchisesPincodeListModel.AuthorisedFranchisesPinCodeListResponseModel
import com.govahan.com.model.authorisedFranchisesStateListModel.AuthorisedFranchisesStateListResponseModel
import com.govahan.com.model.authorizedfranchisesmodel.AuthorizedFranchisesResponseModel
import com.govahan.com.model.bookingloadermodel.BookingLoaderResponseModel
import com.govahan.com.model.bookingpassengelmodel.BookingPassengerResponseModel
import com.govahan.com.model.bookingreviewget.BookingReviewModel
import com.govahan.com.model.bookingreviewpassengerget.BookingReviewPassengerModel
import com.govahan.com.model.cancelledloadertriphistorymodel.CancelledLoaderTripHistoryResponseModel
import com.govahan.com.model.cancelledpassengertriphistorymodel.CancelledPassengerTripHistoryResponseModel
import com.govahan.com.model.completedloadertriphistorymodel.CompletedLoaderTripHistoryResponseModel
import com.govahan.com.model.completedpassengertriphistorymodel.CompletedPassengerTripHistoryResponseModel
import com.govahan.com.model.contactusmodel.ContactUsModel
import com.govahan.com.model.deletefavlocation.DeleteFavLocationModel
import com.govahan.com.model.getPrivacyPolicy.PrivacyPolicyModel
import com.govahan.com.model.getfavouritelocation.GetFavouriteLocationModel
import com.govahan.com.model.getprofile.GetUserProfileModel
import com.govahan.com.model.homebannermodel.HomeBannerResponseModel
import com.govahan.com.model.loaderAddRaiseComplaintModel.LoaderAddRaiseComplaintResponseModel
import com.govahan.com.model.loaderComplaintListDetailModel.LoaderComplaintListDetailResponseModel
import com.govahan.com.model.loaderComplaintlistmodel.LoaderComplaintListResponseModel
import com.govahan.com.model.loaderInvoiceDetailModel.LoaderInvoiceDetailResponseModel
import com.govahan.com.model.loaderInvoiceDownloadModel.LoaderDownloadInvoiceUrlResponseModel
import com.govahan.com.model.loaderRideCompletedModel.LoaderRideCompletedResponseModel
import com.govahan.com.model.loaderaddwalletmodel.LoaderAddWalletResponseModel
import com.govahan.com.model.loadercancelreasonmodel.LoaderCancelReasonListResponseModel
import com.govahan.com.model.loadercanceltripmodel.LoaderTripCancelResponseModel
import com.govahan.com.model.loaderinvoicelistmodel.LoaderInvoiceListResponseModel
import com.govahan.com.model.loaderlivetrackingmodel.LoaderLiveTrackingResponseModel
import com.govahan.com.model.loaderongoinghistorydetailmodel.LoaderOngoingHistoryDetailResponseModel
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessResponseModel
import com.govahan.com.model.loaderrescheduletripmodel.LoaderRescheduleTripResponseModel
import com.govahan.com.model.loadertripmanagementmodel.LoaderTripManagementDetailResponse
import com.govahan.com.model.loaderwalletfiltermodel.LoaderWalletFilterResponseModel
import com.govahan.com.model.loaderwalletlistmodel.LoaderWalletListResponseModel
import com.govahan.com.model.loginOtpModel.LoginOtpResponseModel
import com.govahan.com.model.loginResponse.LoginResponseModel
import com.govahan.com.model.myoffersmodel.MyOffersResponseModel
import com.govahan.com.model.noOfTyrePModel.GetNoOfTyrePModel
import com.govahan.com.model.notificationmodel.NotificationResponseModel
import com.govahan.com.model.ongoingPassengerTripHistoryModel.OngoingPassengerTripHistoryResponseModel
import com.govahan.com.model.ongoingloadertriphistorymodel.OngoingLoaderTripHistoryResponseModel
import com.govahan.com.model.passengerAddRaiseComplaintModel.PassengerAddRaiseComplaintResponseModel
import com.govahan.com.model.passengerComplaintlistdetailmodel.PassengerComplaintListDetailResponseModel
import com.govahan.com.model.passengerComplaintlistmodel.PassengerComplaintListResponseModel
import com.govahan.com.model.passengerInvoiceDetailModel.PassengerInvoiceDetailResponseModel
import com.govahan.com.model.passengerOngoinghistorydetailmodel.PassengerOngoingHistoryDetailResponseModel
import com.govahan.com.model.passengerRideCompletedModel.PassengerRideCompletedResponseModel
import com.govahan.com.model.passengeraddratingmodel.PassengerAddRatingResponseModel
import com.govahan.com.model.passengercancelreasonmodel.PassengerCancelReasonListResponseModel
import com.govahan.com.model.passengercanceltripmodel.PassengerTripCancelResponseModel
import com.govahan.com.model.passengerdownloadinvoicemodel.PassengerDownloadInvoiceUrlResponseModel
import com.govahan.com.model.passengerinvoicelistmodel.PassengerInvoiceListResponseModel
import com.govahan.com.model.passengerlivetrackingmodel.PassengerLiveTrackingResponseModel
import com.govahan.com.model.passengerpaymentsuccessmodel.PassengerPaymentSuccessResponseModel
import com.govahan.com.model.passengertripmanagementmodel.PassengerTripManagementDetailResponse
import com.govahan.com.model.searchPassengerVehicle.SearchPassengerVehicleResponseModel
import com.govahan.com.model.searchauthorisedfranchisesmodel.SearchAuthorisedFranchisesResponseModel
import com.govahan.com.model.searchvehiclemodel.SearchVehicleResponseModel
import com.govahan.com.model.seatingcapacitymodel.GetSeatingCapacityModel
import com.govahan.com.model.sendmailmodel.LoaderSendMailResponseModel
import com.govahan.com.model.settingsmsemailmodel.SettingSmsEmailResponseModel
import com.govahan.com.model.settingwhatsappmodel.SettingWhatsappResponseModel
import com.govahan.com.model.transportownerget.TransportOwnerModel
import com.govahan.com.model.tripmanagementloadermodel.LoaderTripManagementResponseModel
import com.govahan.com.model.tripmanagementpassengermodel.PassengerTripManagementResponseModel
import com.govahan.com.model.truckbodytypeget.TruckBodyTypeModel
import com.govahan.com.model.truckcapacityget.TruckCapacityGetModel
import com.govahan.com.model.trucknooftyreget.TruckNoOfTyreModel
import com.govahan.com.model.truckpricefor_get.TruckPriceForModel
import com.govahan.com.model.trucktypegetmodel.TruckTypeModel
import com.govahan.com.model.vehicletypemodel.GetVehicleTypeModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService : ApiService): MainRepository {

    override suspend fun verifyOTPForLogIn(otp: String, mobile: String,referal:String
    ): Response<LoginOtpResponseModel> = apiService.verifyOTPForLogIn(otp, mobile,referal)

    override suspend fun userLogin(mobile: String,

                                   device_id: String,
                                   device_type: String,
                                   device_name: String,
                                   device_token: String

    ): Response<LoginResponseModel> = apiService.userLogin(mobile,device_id,device_type,device_name,device_token)
    override suspend fun paymentcheckapi(
        token : String,
        transaction_id: String,
        ): Response<PaymentSuccessMsgResponse> = apiService.paymentcheckapi(token,
        transaction_id

    )
    override suspend fun purchase_plan_from_walletApi(header: String,amount:String,transaction_type:String): Response<LoaderAddWalletResponseModel> {
        return apiService.purchase_plan_from_walletApi(header,amount,transaction_type)
    }

    override suspend fun getUserProfile(token : String): Response<GetUserProfileModel> = apiService.getUserProfile(token)
    override suspend fun checksumApi(
        token : String,
        amount: String,
        mobile: String,
        user_id: String,
        ): Response<ChecksumResponse> = apiService.checksumApi(token,
        amount,mobile,user_id

        )

    override suspend fun updateUserProfile(token : String,
                                           name: RequestBody,
                                           email: RequestBody,
                                           mobile_number: RequestBody,
                                           address: RequestBody,
                                           device_token: RequestBody,
                                           device_type: RequestBody,
                                           device_id: RequestBody,
                                           profile_image: MultipartBody.Part?) = apiService.updateUserProfile(token ,name, email, mobile_number
     ,address , device_token , device_type, device_id, profile_image!!)

    override suspend fun contactUsApi(token: String): Response<ContactUsModel> = apiService.contactUsApi(token)
    override suspend fun privacyPolicyApi(token: String): Response<PrivacyPolicyModel> = apiService.privacyPolicyApi(token)
    override suspend fun aboutUs(token: String): Response<PrivacyPolicyModel> = apiService.aboutUs(token)
    override suspend fun calcelation_refund_policy(token: String): Response<PrivacyPolicyModel> = apiService.calcelation_refund_policy(token)
    override suspend fun termsAndConditions(token: String): Response<PrivacyPolicyModel> = apiService.termsAndConditions(token)
    override suspend fun truckTypeApi(token: String): Response<TruckTypeModel> = apiService.truckTypeApi(token)
    override suspend fun truckCapacityApi(token: String): Response<TruckCapacityGetModel> = apiService.truckCapacityApi(token)
    override suspend fun truckBodyTypeApi(token: String): Response<TruckBodyTypeModel> = apiService.truckBodyTypeApi(token)
    override suspend fun truckPriceForApi(token: String): Response<TruckPriceForModel> = apiService.truckPriceForApi(token)
    override suspend fun refer_userApi(
        token: String,
    ): Response<ReferNEarnResponse> =
        apiService.refer_userApi(token)

    override suspend fun searchLoaderVehicleApi(
                                                    token: String,
                                                    task: String,
                                                    pickup_location: String,
                                                    pickup_lat: String,
                                                    pickup_long: String,
                                                    dropup_location: String,
                                                    dropup_lat: String,
                                                    dropup_long: String,
                                                    truck_type: String,
                                                    capacity: String,
                                                    body_type: String,
                                                    wheel: String,

                                                    booking_date: String,
                                                    booking_time: String,

                                                 ): Response<SearchVehicleResponseModel> = apiService.searchLoaderVehicleApi(token, task,
        pickup_location, pickup_lat, pickup_long,dropup_location, dropup_lat, dropup_long, truck_type,
        capacity, body_type, wheel,booking_date,booking_time)

    override suspend fun searchLoaderDetailApi(
        token: String,
        id: String,
        task: String,
        pickup_location: String,
        pickup_lat: String,
        pickup_long: String,
        dropup_location: String,
        dropup_lat: String,
        dropup_long: String,
        booking_date: String,
        available: String

    ): Response<BookingReviewModel> = apiService.searchLoaderDetailApi(token, id, task, pickup_location,
        pickup_lat, pickup_long, dropup_location, dropup_lat, dropup_long, booking_date,available)

    override suspend fun owner_driverDetailApi(
        token: String,
        driver_id: String
    ): Response<TransportOwnerModel> = apiService.owner_driverDetailApi(token, driver_id)

    override suspend fun addRatingApi(
        token: String,
        driver_id: String,
        rating: String,
        desc: String
    ): Response<AddRatingModel> = apiService.addRatingApi(token, driver_id, rating, desc )

    override suspend fun getRatingApi(
        token: String,
        driver_id: String
    ): Response<ReviewsModelClass> = apiService.getRatingApi(token, driver_id)
    override suspend fun my_wallet_payment(header: String,type:String,transaction_id:String,amount:String): Response<LoaderAddWalletResponseModel> {
        return apiService.my_wallet_payment(header,type,transaction_id,amount)
    }

    override suspend fun addFavouriteLocationApi(
        token: String,
        pickup_lat: String,
        pickup_long: String,
        dropup_lat: String,
        dropup_long: String
    ): Response<AddFavouriteLocationModel> = apiService.addFavouriteLocationApi(token,
        pickup_lat, pickup_long, dropup_lat, dropup_long)

    override suspend fun getFavouriteLocationApi(token: String): Response<GetFavouriteLocationModel> = apiService.getFavouriteLocationApi(token)

    override suspend fun vehicleTypeApi(token: String,type: String): Response<GetVehicleTypeModel> = apiService.vehicleTypeApi(token,type)
    override suspend fun seatingCapacityApi(token: String): Response<GetSeatingCapacityModel> = apiService.seatingCapacityApi(token)
    override suspend fun noOfTyrePApi(token: String,type:String): Response<GetNoOfTyrePModel> = apiService.noOfTyrePApi(token,type)

    override suspend fun deleteFavLocationApi(token: String, id: String
    ): Response<DeleteFavLocationModel> = apiService.deleteFavLocationApi(token, id)


    override suspend fun searchPassengerVehicleApi(
        token: String,
        pickup_lat: String,
        pickup_long: String,
        dropup_lat: String,
        dropup_long: String,
        vehicle_type: String,
//        seat: String,
        tyers: String,
        booking_date: String,
        booking_time: String,
        pickup_location : String,
        dropup_location : String,
    ): Response<SearchPassengerVehicleResponseModel> = apiService.searchPassengerVehicleApi(token, pickup_lat, pickup_long,
        dropup_lat, dropup_long, vehicle_type, tyers,booking_date,booking_time,pickup_location,dropup_location)

    override suspend fun searchPassengerDetailApi(
        token: String,
        pickup_lat: String,
        pickup_long: String,
        dropup_lat: String,
        dropup_long: String,
        vehicle_type: String,
        seat: String,
        tyers: String,
        booking_date: String,
        booking_time: String,
        vehicle_id: String,
        id: String
    ): Response<BookingReviewPassengerModel> = apiService.searchPassengerDetailApi(token,
        pickup_lat, pickup_long, dropup_lat, dropup_long,vehicle_type,seat, tyers,booking_date,booking_time,vehicle_id,id)

    override suspend fun bookingPassengerApi(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,
        payment_mode: String,
        booking_date: String,
        booking_time: String,
        driver_id: String,
        booking_relation_id: String
    ): Response<BookingPassengerResponseModel> = apiService.bookingPassengerApi(token,
        pick_up_location, pick_up_lat, pick_up_long, drop_location,drop_lat,drop_long,
        vechicle_id,fare,payment_mode,booking_date,booking_time,driver_id,booking_relation_id)

    override suspend fun bookingLoaderApi(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,
        payment_mode: String,
        booking_date: String,
        booking_time: String,
        driver_id: String,
        body_type: String,
        capacity: String,
        distance: String,
        vehicle_numbers: String,
        booking_relation_id: String

    ): Response<BookingLoaderResponseModel> = apiService.bookingLoaderApi(token,
        pick_up_location, pick_up_lat, pick_up_long, drop_location,drop_lat,drop_long,
        vechicle_id,fare,payment_mode,booking_date,booking_time,driver_id ,body_type , capacity, distance, vehicle_numbers,booking_relation_id)

    override suspend fun getNotificationListApi(token: String): Response<NotificationResponseModel> = apiService.getNotificationListApi(token)

    override suspend fun getLoaderTripManagementApi(token: String): Response<LoaderTripManagementResponseModel> = apiService.getLoaderTripManagementApi(token)
    override suspend fun getPassengerTripManagementApi(token: String): Response<PassengerTripManagementResponseModel> = apiService.getPassengerTripManagementApi(token)
    override suspend fun loaderTripManagementDetailApi(
        token: String,
        booking_id: String
    ): Response<LoaderTripManagementDetailResponse> = apiService.loaderTripManagementDetailApi(token, booking_id)
    override suspend fun getLoaderCancelReasonListApi(token: String): Response<LoaderCancelReasonListResponseModel> = apiService.getLoaderCancelReasonListApi(token)
    override suspend fun loaderTripCancelApi(
        token: String,
        booking_id: String,
        reasons_id: String,
        message: String

    ): Response<LoaderTripCancelResponseModel> = apiService.loaderTripCancelApi(token, booking_id,reasons_id,message )

    override suspend fun loaderRescheduleTripApi(
        token: String,
        booking_id: String,
        booking_date: String,
        booking_time: String
    ): Response<LoaderRescheduleTripResponseModel> = apiService.loaderRescheduleTripApi(token, booking_id,booking_date,booking_time )

    override suspend fun loaderRideCompletedApi(token: String,
        booking_id: String
    ): Response<LoaderRideCompletedResponseModel> = apiService.loaderRideCompletedApi(token, booking_id )

    override suspend fun loaderOngoingBookingTripHistoryApi(
        token: String

    ): Response<OngoingLoaderTripHistoryResponseModel> = apiService.loaderOngoingBookingTripHistoryApi(token)

    override suspend fun loaderCompletedBookingTripHistoryApi(
        token: String

    ): Response<CompletedLoaderTripHistoryResponseModel> = apiService.loaderCompletedBookingTripHistoryApi(token)

    override suspend fun loaderOngoingHistoryDetailApi(
        token: String,
        booking_id: String

    ): Response<LoaderOngoingHistoryDetailResponseModel> = apiService.loaderOngoingHistoryDetailApi(token, booking_id )

    override suspend fun UploadDocumentsResponseApi(
        token: String,
        booking_id: String

    ): Response<UploadDocumentsResponse> = apiService.UploadDocumentsResponseApi(token, booking_id )

    override suspend fun loaderInvoiceListApi(
        token: String

    ): Response<LoaderInvoiceListResponseModel> = apiService.loaderInvoiceListApi(token )

    override suspend fun loaderInvoiceDetailApi(
        token: String,
        invoice_numbers: String

    ): Response<LoaderInvoiceDetailResponseModel> = apiService.loaderInvoiceDetailApi(token, invoice_numbers )

    override suspend fun loaderPaymentSuccessApi(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,total_fare : String,
        payment_mode: String,
        booking_date: String,
        booking_time: String,
        driver_id: String,
        dis: String,
        body_type: String,
        capacity: String,
        distance: String,
        vehicle_numbers: String,
        transaction_id: String,
        payment_status: String,
        currency: String,
        booking_relation_id: String
    ): Response<LoaderPaymentSuccessResponseModel> = apiService.loaderPaymentSuccessApi(token, pick_up_location,
        pick_up_lat,pick_up_long , drop_location,drop_lat , drop_long, vechicle_id,fare,total_fare,payment_mode,
        booking_date  , booking_time  ,driver_id ,dis ,body_type,capacity , distance,vehicle_numbers , transaction_id,
        payment_status, currency,booking_relation_id)
    override suspend fun loader_payment_wallet(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,total_fare : String,
        payment_mode: String,
        booking_date: String,
        booking_time: String,
        driver_id: String,
        dis: String,
        body_type: String,
        capacity: String,
        distance: String,
        vehicle_numbers: String,
        payment_status: String,
        currency: String,
        booking_relation_id: String,
    ): Response<LoaderPaymentSuccessResponseModel> = apiService.loader_payment_wallet(token, pick_up_location,
        pick_up_lat,pick_up_long , drop_location,drop_lat , drop_long, vechicle_id,fare,total_fare,payment_mode,
        booking_date  ,booking_time,driver_id ,dis ,body_type,capacity , distance,vehicle_numbers ,
        payment_status, currency,booking_relation_id)
    override suspend fun passenenger_payment_wallet(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,total_fare : String,
        payment_mode: String,
        booking_date: String,
        booking_time: String,
        driver_id: String,
        dis: String,
        body_type: String,
        capacity: String,
        distance: String,
        vehicle_numbers: String,
        payment_status: String,
        currency: String,
        booking_relation_id: String
    ): Response<LoaderPaymentSuccessResponseModel> = apiService.passenenger_payment_wallet(token, pick_up_location,
        pick_up_lat,pick_up_long , drop_location,drop_lat , drop_long, vechicle_id,fare,total_fare,payment_mode,
        booking_date  ,booking_time  ,driver_id ,dis ,body_type,capacity , distance,vehicle_numbers ,
        payment_status, currency,booking_relation_id)

    override suspend fun loaderWalletAddMoneyApi(
        token: String,
        amount: String
    ): Response<LoaderAddWalletResponseModel> = apiService.loaderWalletAddMoneyApi(token, amount )


    override suspend fun loaderWalletListApi(token: String): Response<LoaderWalletListResponseModel> = apiService.loaderWalletListApi(token)

    override suspend fun my_wallet_list_download(token: String): Response<LoaderWalletListResponseModel> = apiService.my_wallet_list_download(token)

    override suspend fun loaderWalletFilterApi(token: String,
                                               date: String,transaction_type:String): Response<LoaderWalletFilterResponseModel> = apiService.loaderWalletFilterApi(token, date,transaction_type)

    override suspend fun loaderAddRaiseComplaintApi(token: String,
                                                    booking_id: String,
                                                    com_message: String
    ): Response<LoaderAddRaiseComplaintResponseModel> = apiService.loaderAddRaiseComplaintApi(token, booking_id, com_message)

    override suspend fun complaint_resolved(token: String,
                                                    id: String,
                                                    type: String
    ): Response<LoaderAddRaiseComplaintResponseModel> = apiService.complaint_resolved(token, id, type)

    override suspend fun loaderComplaintListApi(token: String): Response<LoaderComplaintListResponseModel> = apiService.loaderComplaintListApi(token)
    override suspend fun user_online_transaction_historyApi(token: String): Response<TransactionReportResponse> = apiService.user_online_transaction_historyApi(token)

    override suspend fun loaderComplaintListDetailApi(token: String,
                                                      booking_id: String): Response<LoaderComplaintListDetailResponseModel> = apiService.loaderComplaintListDetailApi(token, booking_id)

    override suspend fun loaderLiveTrackingApi(token: String,
                                                      booking_id: String): Response<LoaderLiveTrackingResponseModel> = apiService.loaderLiveTrackingApi(token, booking_id)
    override suspend fun search_loader_driver_review(token: String,
                                                      driver_id: String): Response<ReviewsModelClass> = apiService.search_loader_driver_review(token, driver_id)

    override suspend fun passengerPaymentSuccessApi(
        token: String,
        pick_up_location: String,
        pick_up_lat: String,
        pick_up_long: String,
        drop_location: String,
        drop_lat: String,
        drop_long: String,
        vechicle_id: String,
        fare: String,total_fare : String,
        payment_mode: String,
        booking_date: String,
        driver_id: String,
        dis: String,
        body_type: String,
        capacity: String,
        distance: String,
        vehicle_numbers: String,
        transaction_id: String,
        payment_status: String,
        currency: String,
        booking_relation_id: String,
    ): Response<PassengerPaymentSuccessResponseModel> = apiService.passengerPaymentSuccessApi(token, pick_up_location,
        pick_up_lat,pick_up_long , drop_location,drop_lat , drop_long, vechicle_id,fare,total_fare,payment_mode,
        booking_date  ,driver_id ,dis ,body_type,capacity , distance,vehicle_numbers , transaction_id,
        payment_status, currency,booking_relation_id)

    override suspend fun passengerTripManagementDetailApi(
        token: String,
        booking_id: String

    ): Response<PassengerTripManagementDetailResponse> = apiService.passengerTripManagementDetailApi(token, booking_id)

    override suspend fun getPassengerCancelReasonListApi(token: String): Response<PassengerCancelReasonListResponseModel> = apiService.getPassengerCancelReasonListApi(token)

    override suspend fun passengerTripCancelApi(
        token: String,
        booking_id: String,
        reasons_id: String,
        message: String

    ): Response<PassengerTripCancelResponseModel> = apiService.passengerTripCancelApi(token, booking_id,reasons_id,message )

    override suspend fun getDashboardBannertApi(token: String): Response<HomeBannerResponseModel> = apiService.getDashboardBannertApi(token)

    override suspend fun passengerRideCompletedApi(
        token: String,
        booking_id: String

    ): Response<PassengerRideCompletedResponseModel> = apiService.passengerRideCompletedApi(token, booking_id )

    override suspend fun passengerAddRatingApi(
        token: String,
        driver_id: String,
        rating: String,
        desc: String,
        user_id: String
    ): Response<PassengerAddRatingResponseModel> = apiService.passengerAddRatingApi(token, driver_id, rating, desc, user_id )

    override suspend fun getAuthorizedFranchisesApi(token: String): Response<AuthorizedFranchisesResponseModel> = apiService.getAuthorizedFranchisesApi(token)


    override suspend fun passengerOngoingBookingTripHistoryApi(
        token: String

    ): Response<OngoingPassengerTripHistoryResponseModel> = apiService.passengerOngoingBookingTripHistoryApi(token)

    override suspend fun passengerCompletedBookingTripHistoryApi(
        token: String

    ): Response<CompletedPassengerTripHistoryResponseModel> = apiService.passengerCompletedBookingTripHistoryApi(token)

    override suspend fun passengerOngoingHistoryDetailApi(
        token: String,
        booking_id: String

    ): Response<PassengerOngoingHistoryDetailResponseModel> = apiService.passengerOngoingHistoryDetailApi(token, booking_id )

    override suspend fun passengerInvoiceListApi(
        token: String

    ): Response<PassengerInvoiceListResponseModel> = apiService.passengerInvoiceListApi(token )

  override suspend fun passengerInvoiceDetailApi(
        token: String,  invoice_numbers : String

  ): Response<PassengerInvoiceDetailResponseModel> = apiService.passengerInvoiceDetailApi(token, invoice_numbers )

    override suspend fun passengerLiveTrackingApi(token: String,
                                               booking_id: String): Response<PassengerLiveTrackingResponseModel> = apiService.passengerLiveTrackingApi(token, booking_id)

    override suspend fun passengerAddRaiseComplaintApi(token: String,
                                                    booking_id: String,
                                                    com_message: String
    ): Response<PassengerAddRaiseComplaintResponseModel> = apiService.passengerAddRaiseComplaintApi(token, booking_id, com_message)


    override suspend fun passengerComplaintListApi(token: String): Response<PassengerComplaintListResponseModel> = apiService.passengerComplaintListApi(token)


    override suspend fun passengerComplaintListDetailApi(token: String,
                                                      booking_id: String): Response<PassengerComplaintListDetailResponseModel> = apiService.passengerComplaintListDetailApi(token, booking_id)

 override suspend fun getMyOffersApi(token: String): Response<MyOffersResponseModel> = apiService.getMyOffersApi(token)

    override suspend fun searchAuthorizedFranchisesApi(token: String, state_id : String, district_id : String, pin_code : String): Response<SearchAuthorisedFranchisesResponseModel> = apiService.searchAuthorizedFranchisesApi(token, state_id, district_id, pin_code )

    override suspend fun sendMailLoaderInvoiceApi(token: String, booking_id : String): Response<LoaderSendMailResponseModel> = apiService.sendMailLoaderInvoiceApi(token, booking_id )
    override suspend fun sendMailPassengerInvoiceApi(token: String, booking_id : String): Response<LoaderSendMailResponseModel> = apiService.sendMailPassengerInvoiceApi(token, booking_id)

    override suspend fun loaderCancelledBookingTripHistoryApi(
        token: String
    ): Response<CancelledLoaderTripHistoryResponseModel> = apiService.loaderCancelledBookingTripHistoryApi(token)

  override suspend fun passengerCancelledBookingTripHistoryApi(
        token: String
    ): Response<CancelledPassengerTripHistoryResponseModel> = apiService.passengerCancelledBookingTripHistoryApi(token)

override suspend fun passengerDownloadInvoiceUrlApi(
        token: String, booking_id : String
    ): Response<PassengerDownloadInvoiceUrlResponseModel> = apiService.passengerDownloadInvoiceUrlApi(token, booking_id)

override suspend fun loaderDownloadInvoiceUrlApi(
        token: String, booking_id : String
    ): Response<LoaderDownloadInvoiceUrlResponseModel> = apiService.loaderDownloadInvoiceUrlApi(token, booking_id)
override suspend fun loader_invoice_url_second(
        token: String, booking_id : String
    ): Response<LoaderDownloadInvoiceUrlResponseModel> = apiService.loader_invoice_url_second(token, booking_id)
override suspend fun passenenger_invoice_url_second(
        token: String, booking_id : String
    ): Response<LoaderDownloadInvoiceUrlResponseModel> = apiService.passenenger_invoice_url_second(token, booking_id)

    override suspend fun getAuthorisedFranchisesStateListApi(
        token: String
    ): Response<AuthorisedFranchisesStateListResponseModel> = apiService.getAuthorisedFranchisesStateListApi(token)

    override suspend fun getAuthorisedFranchisesDisttListApi(
        token: String, state_id : String
    ): Response<AuthorisedFranchisesDisttListResponseModel> = apiService.getAuthorisedFranchisesDisttListApi(token, state_id)
    
    override suspend fun getAuthorisedFranchisesPincodeListApi(
        token: String, city_id : String
    ): Response<AuthorisedFranchisesPinCodeListResponseModel> = apiService.getAuthorisedFranchisesPincodeListApi(token, city_id)
    override suspend fun vendor_number_vehicle_list(
        token: String, id : String
    ): Response<AuthorizedFranchiseDetailsApi> = apiService.vendor_number_vehicle_list(token, id)

 override suspend fun settingsWhatsappUpdatesApi(
        token: String, state_id : String
    ): Response<SettingWhatsappResponseModel> = apiService.settingsWhatsappUpdatesApi(token, state_id)


 override suspend fun settingsSmsEmailUpdatesApi(
        token: String, state_id : String
    ): Response<SettingSmsEmailResponseModel> = apiService.settingsSmsEmailUpdatesApi(token, state_id)










}