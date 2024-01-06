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
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("user_login")
    suspend fun userLogin(
        @Field("mobile") mobile : String,
        @Field("device_id") device_id : String,
        @Field("device_type") device_type : String,
        @Field("device_name") device_name : String,
        @Field("device_token") device_token : String,
    ): Response<LoginResponseModel>

    @FormUrlEncoded
    @POST("user_verfiy_otp")
    suspend fun verifyOTPForLogIn(
        @Field("otp") otp : String,
        @Field("mobile_number") mobile : String,
        @Field("referal_code") referal_code : String,): Response<LoginOtpResponseModel>

    @GET("about_us")
    suspend fun aboutUs(
        @Header("Authorization") authorization: String
    ): Response<PrivacyPolicyModel>

    @GET("calcelation_refund_policy")
    suspend fun calcelation_refund_policy(
        @Header("Authorization") authorization: String
    ): Response<PrivacyPolicyModel>

    @FormUrlEncoded
    @POST("phonepay-check-status")
    suspend fun paymentcheckapi(
        @Header("Authorization") authorization: String,
        @Field("transaction_id") amount: String,
    ): Response<PaymentSuccessMsgResponse>

    @FormUrlEncoded
    @POST("purchase_plan_from_wallet")
    suspend fun purchase_plan_from_walletApi(
        @Header("Authorization") authorization: String,
        @Field("plan_amount") plan_amount:String,
        @Field("transaction_type") transaction_type:String,
        ): Response<LoaderAddWalletResponseModel>

    @GET("get_user_profile")
    suspend fun getUserProfile(@Header("Authorization") token : String): Response<GetUserProfileModel>
    @FormUrlEncoded
    @POST("phonepay")
    suspend fun checksumApi(
        @Header("Authorization") authorization: String,
        @Field("amount") amount: String,
        @Field("mobile") mobile: String,
        @Field("user_id") user_id: String,
        ): Response<ChecksumResponse>

    @Multipart
    @POST("user_update_profiile")
    suspend fun updateUserProfile(
        @Header("Authorization") token : String,
        @Part("name") name : RequestBody,
        @Part("email") email : RequestBody,
        @Part("mobile_number") mobile_number : RequestBody,
        @Part("address") address : RequestBody,
        @Part("device_token") device_token : RequestBody,
        @Part("device_type") device_type : RequestBody,
        @Part("device_id") device_id : RequestBody,
        @Part profile_image : MultipartBody.Part): Response<LoginOtpResponseModel>

    @POST("refer_user")
    suspend fun refer_userApi(
        @Header("Authorization") authorization: String,
    ): Response<ReferNEarnResponse>

    @GET("user_contact_us")
    suspend fun contactUsApi(@Header("Authorization") token : String): Response<ContactUsModel>

    @GET("user_privacypolicy")
    suspend fun privacyPolicyApi(@Header("Authorization") token : String): Response<PrivacyPolicyModel>

    @GET("user_termcondition")
    suspend fun termsAndConditions(@Header("Authorization") token : String): Response<PrivacyPolicyModel>

    @GET("get_truck_type")
    suspend fun truckTypeApi(@Header("Authorization") token : String): Response<TruckTypeModel>

    @GET("get_truck_type")
    suspend fun refernearnApi(@Header("Authorization") token : String): Response<TruckTypeModel>

    @GET("get_capacity")
    suspend fun truckCapacityApi(@Header("Authorization") token : String): Response<TruckCapacityGetModel>

    @GET("get_body_type")
    suspend fun truckBodyTypeApi(@Header("Authorization") token : String): Response<TruckBodyTypeModel>

    @GET("get_wheels")
    suspend fun truckNoOfTyreApi(@Header("Authorization") token : String): Response<TruckNoOfTyreModel>

    @GET("get_wheels")
    suspend fun truckPriceForApi(@Header("Authorization") token : String): Response<TruckPriceForModel>

    @FormUrlEncoded
    @POST("search_loader")
    suspend fun searchLoaderVehicleApi(@Header("Authorization") token : String,
                                 @Field("task") task : String,
                                 @Field("pickup_location") pickup_location : String,
                                 @Field("pickup_lat") pickup_lat : String,
                                 @Field("pickup_long") pickup_long : String,
                                 @Field("dropup_location") dropup_location : String,
                                 @Field("dropup_lat") dropup_lat : String,
                                 @Field("dropup_long") dropup_long : String,
                                 @Field("truck_type") truck_type : String,
                                 @Field("capacity") capacity : String,
                                 @Field("body_type") body_type : String,
                                 @Field("tyres") tyres : String,
                                 @Field("booking_date") booking_date : String,
                                 @Field("booking_time") booking_time : String,
                            ) : Response<SearchVehicleResponseModel>

    @FormUrlEncoded
    @POST("search_loader_detail")
    suspend fun searchLoaderDetailApi(@Header("Authorization") token : String,
                                      @Field("id") id : String,
                                      @Field("task") task : String,
                                      @Field("pickup_location") pickup_location : String,
                                      @Field("pickup_lat") pickup_lat : String,
                                      @Field("pickup_long") pickup_long : String,
                                      @Field("dropup_location") dropup_location : String,
                                      @Field("dropup_lat") dropup_lat : String,
                                      @Field("dropup_long") dropup_long : String,
                                      @Field("booking_date") booking_date : String,
                                      @Field("available") available : String
    ) : Response<BookingReviewModel>

    @FormUrlEncoded
    @POST("driver_detail")
    suspend fun owner_driverDetailApi(@Header("Authorization") token : String,
                                      @Field("driver_id") driver_id : String
    ) : Response<TransportOwnerModel>

    @FormUrlEncoded
    @POST("add_rating")
    suspend fun addRatingApi(@Header("Authorization") token : String,
                                      @Field("driver_id") driver_id : String,
                                      @Field("rating") rating : String,
                                      @Field("desc") desc : String
                                ) : Response<AddRatingModel>

    @FormUrlEncoded
    @POST("get_rating")
    suspend fun getRatingApi(@Header("Authorization") token : String,
                                       @Field("driver_id") driver_id : String
                               ) : Response<ReviewsModelClass>

    @FormUrlEncoded
    @POST("favorite_location")
    suspend fun addFavouriteLocationApi(@Header("Authorization") token : String,
                                       @Field("pickup_lat") pickup_lat : String,
                                       @Field("pickup_long") pickup_long : String,
                                       @Field("dropup_lat") dropup_lat : String,
                                       @Field("dropup_long") dropup_long : String
                                 ) : Response<AddFavouriteLocationModel>

    @GET("get_favorite_location")
    suspend fun getFavouriteLocationApi(@Header("Authorization") token : String): Response<GetFavouriteLocationModel>
    @FormUrlEncoded
    @POST("vehicle_type")
    suspend fun vehicleTypeApi(@Header("Authorization") token : String,
                               @Field("type") type : String): Response<GetVehicleTypeModel>

    @GET("vehicle_seat")
    suspend fun seatingCapacityApi(@Header("Authorization") token : String): Response<GetSeatingCapacityModel>

    @FormUrlEncoded
    @POST("vehicle_wheels")
    suspend fun noOfTyrePApi(@Header("Authorization") token : String,
        @Field("type") type: String): Response<GetNoOfTyrePModel>

    @FormUrlEncoded
    @POST("del_favorite_location")
    suspend fun deleteFavLocationApi(@Header("Authorization") token : String,
                             @Field("id") id : String
    )
    : Response<DeleteFavLocationModel>

    @FormUrlEncoded
    @POST("search_passenenger_vehicle")
    suspend fun searchPassengerVehicleApi(@Header("Authorization") token : String,
                                       @Field("pickup_lat") pickup_lat : String,
                                       @Field("pickup_long") pickup_long : String,
                                       @Field("dropup_lat") dropup_lat : String,
                                       @Field("dropup_long") dropup_long : String,
                                       @Field("vehicle_type") vehicle_type : String,
//                                       @Field("seat") seat : String,
                                       @Field("tyers") tyers : String,
                                       @Field("booking_date") booking_date : String,
                                       @Field("booking_time") booking_time : String,
                                       @Field("pickup_location") pickup_location : String,
                                       @Field("dropup_location") dropup_location : String,
                                       ) : Response<SearchPassengerVehicleResponseModel>
    @FormUrlEncoded
    @POST("search_passenenger_vehicle_details")
    suspend fun searchPassengerDetailApi(@Header("Authorization") token : String,
                                      @Field("pickup_lat") pickup_lat : String,
                                      @Field("pickup_long") pickup_long : String,
                                      @Field("dropup_lat") dropup_lat : String,
                                      @Field("dropup_long") dropup_long : String,
                                      @Field("vehicle_type") vehicle_type : String,
                                      @Field("seat") seat : String,
                                      @Field("tyers") tyers : String,
                                      @Field("booking_date") booking_date : String,
                                      @Field("booking_time") booking_time : String,
                                      @Field("vehicle_id") vehicle_id : String,
                                      @Field("id") id : String
    ) : Response<BookingReviewPassengerModel>

    @FormUrlEncoded
    @POST("booking_passengers")
    suspend fun bookingPassengerApi(@Header("Authorization") token : String,
                                         @Field("pick_up_location") pick_up_location : String,
                                         @Field("pick_up_lat") pick_up_lat : String,
                                         @Field("pick_up_long") pick_up_long : String,
                                         @Field("drop_location") drop_location : String,
                                         @Field("drop_lat") drop_lat : String,
                                         @Field("drop_long") drop_long : String,
                                         @Field("vechicle_id") vechicle_id : String,
                                         @Field("fare") fare : String,
                                         @Field("payment_mode") payment_mode : String,
                                         @Field("booking_date") booking_date : String,
                                         @Field("booking_time") booking_time : String,
                                         @Field("driver_id") driver_id : String,
                                         @Field("booking_relation_id") booking_relation_id : String
    ) : Response<BookingPassengerResponseModel>

    @FormUrlEncoded
    @POST("booking_loader")
    suspend fun bookingLoaderApi(@Header("Authorization") token : String,
                                    @Field("pick_up_location") pick_up_location : String,
                                    @Field("pick_up_lat") pick_up_lat : String,
                                    @Field("pick_up_long") pick_up_long : String,
                                    @Field("drop_location") drop_location : String,
                                    @Field("drop_lat") drop_lat : String,
                                    @Field("drop_long") drop_long : String,
                                    @Field("vechicle_id") vechicle_id : String,
                                    @Field("fare") fare : String,
                                    @Field("payment_mode") payment_mode : String,
                                    @Field("booking_date") booking_date : String,
                                    @Field("booking_time") booking_time : String,
                                    @Field("driver_id") driver_id : String,
                                    @Field("body_type") body_type : String,
                                    @Field("capacity") capacity : String,
                                    @Field("distance") distance : String,
                                    @Field("vehicle_numbers") vehicle_numbers : String,
                                    @Field("booking_relation_id") booking_relation_id : String
    ) : Response<BookingLoaderResponseModel>

    @GET("notification_list")
    suspend fun getNotificationListApi(@Header("Authorization") token : String): Response<NotificationResponseModel>

    @GET("trip_management_loader")
    suspend fun getLoaderTripManagementApi(@Header("Authorization") token : String): Response<LoaderTripManagementResponseModel>

    @FormUrlEncoded
    @POST("trip_management_loader_details")
    suspend fun loaderTripManagementDetailApi(@Header("Authorization") token : String,
                                      @Field("booking_id") booking_id : String
                                     ) : Response<LoaderTripManagementDetailResponse>

    @GET("loder_cancel_reasons_list")
    suspend fun getLoaderCancelReasonListApi(@Header("Authorization") token : String): Response<LoaderCancelReasonListResponseModel>

    @FormUrlEncoded
    @POST("loader_trip_cancel")
    suspend fun loaderTripCancelApi(@Header("Authorization") token : String,
                                              @Field("booking_id") booking_id : String,
                                              @Field("reasons_id") reasons_id : String,
                                              @Field("message") message : String
    ) : Response<LoaderTripCancelResponseModel>

    @FormUrlEncoded
    @POST("reschedule_trip")
    suspend fun loaderRescheduleTripApi(@Header("Authorization") token : String,
                                    @Field("booking_id") booking_id : String,
                                    @Field("booking_date") booking_date : String,
                                    @Field("booking_time") booking_time : String
    ) : Response<LoaderRescheduleTripResponseModel>

    @FormUrlEncoded
    @POST("lode_ride_completed")
    suspend fun loaderRideCompletedApi(@Header("Authorization") token : String,
                                    @Field("booking_id") booking_id : String
    ) : Response<LoaderRideCompletedResponseModel>

  //  @FormUrlEncoded
    @GET("ongoing_booking_history_loader")
    suspend fun loaderOngoingBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<OngoingLoaderTripHistoryResponseModel>

  // @FormUrlEncoded
    @GET("completed_booking_history_loader")
    suspend fun loaderCompletedBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<CompletedLoaderTripHistoryResponseModel>

    @FormUrlEncoded
    @POST("completed_booking_history_loader_details")
    suspend fun loaderOngoingHistoryDetailApi(@Header("Authorization") token : String,
                                              @Field("booking_id") booking_id : String
    ) : Response<LoaderOngoingHistoryDetailResponseModel>

    @GET("loader_invoice_list")
    suspend fun loaderInvoiceListApi(@Header("Authorization") token : String
    ) : Response<LoaderInvoiceListResponseModel>

    @FormUrlEncoded
    @POST("loader_invoice_details")
    suspend fun loaderInvoiceDetailApi(@Header("Authorization") token : String,
                                              @Field("invoice_numbers") invoice_numbers : String
    ) : Response<LoaderInvoiceDetailResponseModel>

    @FormUrlEncoded
    @POST("loader_invoice")
    suspend fun UploadDocumentsResponseApi(@Header("Authorization") token : String,
                                              @Field("booking_id") booking_id : String
    ) : Response<UploadDocumentsResponse>

    @FormUrlEncoded
    @POST("loader_payment")
    suspend fun loaderPaymentSuccessApi(@Header("Authorization") token : String,
                                     @Field("pick_up_location") pick_up_location : String,
                                     @Field("pick_up_lat") pick_up_lat : String,
                                     @Field("pick_up_long") pick_up_long : String,
                                     @Field("drop_location") drop_location : String,
                                     @Field("drop_lat") drop_lat : String,
                                     @Field("drop_long") drop_long : String,
                                     @Field("vechicle_id") vechicle_id : String,
                                     @Field("fare") fare : String,
                                     @Field("fare_total") fare_total : String,
                                     @Field("payment_mode") payment_mode : String,
                                     @Field("booking_date") booking_date : String,
                                     @Field("booking_time") booking_time : String,
                                     @Field("driver_id") driver_id : String,
                                     @Field("dis") dis : String,
                                     @Field("body_type") body_type : String,
                                     @Field("capacity") capacity : String,
                                     @Field("distance") distance : String,
                                     @Field("vehicle_numbers") vehicle_numbers : String,
                                     @Field("transaction_id") transaction_id : String,
                                     @Field("payment_status") payment_status : String,
                                     @Field("currency") currency : String,
                                        @Field("booking_relation_id") booking_relation_id : String
    ) : Response<LoaderPaymentSuccessResponseModel>

    @FormUrlEncoded
    @POST("loader_payment_wallet")
    suspend fun loader_payment_wallet(@Header("Authorization") token : String,
                                     @Field("pick_up_location") pick_up_location : String,
                                     @Field("pick_up_lat") pick_up_lat : String,
                                     @Field("pick_up_long") pick_up_long : String,
                                     @Field("drop_location") drop_location : String,
                                     @Field("drop_lat") drop_lat : String,
                                     @Field("drop_long") drop_long : String,
                                     @Field("vechicle_id") vechicle_id : String,
                                     @Field("fare") fare : String,
                                      @Field("fare_total") fare_total : String,
                                      @Field("payment_mode") payment_mode : String,
                                     @Field("booking_date") booking_date : String,
                                     @Field("booking_time") booking_time : String,
                                     @Field("driver_id") driver_id : String,
                                     @Field("dis") dis : String,
                                     @Field("body_type") body_type : String,
                                     @Field("capacity") capacity : String,
                                     @Field("distance") distance : String,
                                     @Field("vehicle_numbers") vehicle_numbers : String,
                                     @Field("payment_status") payment_status : String,
                                     @Field("currency") currency : String,
                                     @Field("booking_relation_id") booking_relation_id : String
                                     ) : Response<LoaderPaymentSuccessResponseModel>

    @FormUrlEncoded
    @POST("passenenger_payment_wallet")
    suspend fun passenenger_payment_wallet(@Header("Authorization") token : String,
                                     @Field("pick_up_location") pick_up_location : String,
                                     @Field("pick_up_lat") pick_up_lat : String,
                                     @Field("pick_up_long") pick_up_long : String,
                                     @Field("drop_location") drop_location : String,
                                     @Field("drop_lat") drop_lat : String,
                                     @Field("drop_long") drop_long : String,
                                     @Field("vechicle_id") vechicle_id : String,
                                     @Field("fare") fare : String,
                                     @Field("fare_total") fare_total : String,
                                     @Field("payment_mode") payment_mode : String,
                                     @Field("booking_date") booking_date : String,
                                     @Field("booking_time") booking_time : String,
                                     @Field("driver_id") driver_id : String,
                                     @Field("dis") dis : String,
                                     @Field("body_type") body_type : String,
                                     @Field("capacity") capacity : String,
                                     @Field("distance") distance : String,
                                     @Field("vehicle_numbers") vehicle_numbers : String,
                                     @Field("payment_status") payment_status : String,
                                     @Field("currency") currency : String,
                                     @Field("booking_relation_id") booking_relation_id : String

    ) : Response<LoaderPaymentSuccessResponseModel>



    @FormUrlEncoded
    @POST("add_my_wallet")
    suspend fun loaderWalletAddMoneyApi(@Header("Authorization") token : String,
                                        @Field("amount") amount : String
    ) : Response<LoaderAddWalletResponseModel>

    //  @FormUrlEncoded
    @GET("my_wallet_list")
    suspend fun loaderWalletListApi(@Header("Authorization") token : String
    ) : Response<LoaderWalletListResponseModel>
    @GET("my_wallet_list_download")
    suspend fun my_wallet_list_download(@Header("Authorization") token : String
    ) : Response<LoaderWalletListResponseModel>

    @FormUrlEncoded
    @POST("my_wallet_filter_user_list")
    suspend fun loaderWalletFilterApi(@Header("Authorization") token : String,
                                      @Field("date") date : String, @Field("transaction_type")
                                     transaction_type : String
    ) : Response<LoaderWalletFilterResponseModel>

    @FormUrlEncoded
    @POST("loader_add_raise_complaint")
    suspend fun loaderAddRaiseComplaintApi(@Header("Authorization") token : String,
                                        @Field("booking_id") booking_id : String,
                                        @Field("com_message") com_message : String
    ) : Response<LoaderAddRaiseComplaintResponseModel>

    @FormUrlEncoded
    @POST("complaint_resolved")
    suspend fun complaint_resolved(@Header("Authorization") token : String,
                                        @Field("id") booking_id : String,
                                        @Field("type") com_message : String
    ) : Response<LoaderAddRaiseComplaintResponseModel>

    //  @FormUrlEncoded
    @GET("loader_raise_complaint_list")
    suspend fun loaderComplaintListApi(@Header("Authorization") token : String
    ) : Response<LoaderComplaintListResponseModel>

    @GET("user_online_transaction_history")
    suspend fun user_online_transaction_historyApi(@Header("Authorization") token : String
    ) : Response<TransactionReportResponse>

    @FormUrlEncoded
    @POST("loader_raise_complaint_list_details")
    suspend fun loaderComplaintListDetailApi(@Header("Authorization") token : String,
                                           @Field("booking_id") booking_id : String
    ) : Response<LoaderComplaintListDetailResponseModel>

    @FormUrlEncoded
    @POST("loader_live_tracking")
    suspend fun loaderLiveTrackingApi(@Header("Authorization") token : String,
                                             @Field("booking_id") booking_id : String
    ) : Response<LoaderLiveTrackingResponseModel>

    @FormUrlEncoded
    @POST("search_loader_driver_review")
    suspend fun search_loader_driver_review(@Header("Authorization") token : String,
                                             @Field("driver_id") driver_id : String
    ) : Response<ReviewsModelClass>

    @FormUrlEncoded
    @POST("passenenger_payment")
    suspend fun passengerPaymentSuccessApi(@Header("Authorization") token : String,
                                        @Field("pick_up_location") pick_up_location : String,
                                        @Field("pick_up_lat") pick_up_lat : String,
                                        @Field("pick_up_long") pick_up_long : String,
                                        @Field("drop_location") drop_location : String,
                                        @Field("drop_lat") drop_lat : String,
                                        @Field("drop_long") drop_long : String,
                                        @Field("vechicle_id") vechicle_id : String,
                                        @Field("fare") fare : String,
                                        @Field("fare_total") fare_total : String,
                                        @Field("payment_mode") payment_mode : String,
                                        @Field("booking_date") booking_date : String,
                                        @Field("driver_id") driver_id : String,
                                        @Field("dis") dis : String,
                                        @Field("body_type") body_type : String,
                                        @Field("capacity") capacity : String,
                                        @Field("distance") distance : String,
                                        @Field("vehicle_numbers") vehicle_numbers : String,
                                        @Field("transaction_id") transaction_id : String,
                                        @Field("payment_status") payment_status : String,
                                        @Field("currency") currency : String,
          @Field("booking_relation_id") booking_relation_id : String
    ) : Response<PassengerPaymentSuccessResponseModel>

    @GET("trip_management_passenenger")
    suspend fun getPassengerTripManagementApi(@Header("Authorization") token : String): Response<PassengerTripManagementResponseModel>

    @FormUrlEncoded
    @POST("trip_management_passenenger_details")
    suspend fun passengerTripManagementDetailApi(@Header("Authorization") token : String,
                                              @Field("booking_id") booking_id : String
    ) : Response<PassengerTripManagementDetailResponse>

    @GET("passengers_cancel_reasons_list")
    suspend fun getPassengerCancelReasonListApi(@Header("Authorization") token : String): Response<PassengerCancelReasonListResponseModel>

    @FormUrlEncoded
    @POST("passengers_trip_cancel")
    suspend fun passengerTripCancelApi(@Header("Authorization") token : String,
                                    @Field("booking_id") booking_id : String,
                                    @Field("reasons_id") reasons_id : String,
                                    @Field("message") message : String
    ) : Response<PassengerTripCancelResponseModel>

    @GET("dashboard_banner")
    suspend fun getDashboardBannertApi(@Header("Authorization") token : String): Response<HomeBannerResponseModel>

    @FormUrlEncoded
    @POST("passengers_ride_completed")
    suspend fun passengerRideCompletedApi(@Header("Authorization") token : String,
                                          @Field("booking_id") booking_id : String
    ) : Response<PassengerRideCompletedResponseModel>

    @FormUrlEncoded
    @POST("passengers_add_rating")
    suspend fun passengerAddRatingApi(@Header("Authorization") token : String,
                             @Field("driver_id") driver_id : String,
                             @Field("rating") rating : String,
                             @Field("desc") desc : String,
                             @Field("user_id") user_id : String
    ) : Response<PassengerAddRatingResponseModel>

    @GET("vendor_number_vehicle")
    suspend fun getAuthorizedFranchisesApi(@Header("Authorization") token : String): Response<AuthorizedFranchisesResponseModel>

    @GET("passenenger_ongoing_booking")
    suspend fun passengerOngoingBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<OngoingPassengerTripHistoryResponseModel>

    @GET("passenenger_completed_booking")
    suspend fun passengerCompletedBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<CompletedPassengerTripHistoryResponseModel>

    @FormUrlEncoded
    @POST("passenenger_booking_details")
    suspend fun passengerOngoingHistoryDetailApi(@Header("Authorization") token : String,
                                              @Field("booking_id") booking_id : String
    ) : Response<PassengerOngoingHistoryDetailResponseModel>

    /*------------------------------------------------*/
    @GET("passenenger_invoice_list")
    suspend fun passengerInvoiceListApi(@Header("Authorization") token : String
    ) : Response<PassengerInvoiceListResponseModel>

    @FormUrlEncoded
    @POST("passenenger_invoice_details")
    suspend fun passengerInvoiceDetailApi(@Header("Authorization") token : String,
                                       @Field("invoice_numbers") invoice_numbers : String
    ) : Response<PassengerInvoiceDetailResponseModel>

    @FormUrlEncoded
    @POST("passenenger_live_tracking")
    suspend fun passengerLiveTrackingApi(@Header("Authorization") token : String,
                                      @Field("booking_id") booking_id : String
    ) : Response<PassengerLiveTrackingResponseModel>

    @FormUrlEncoded
    @POST("passenenger_add_raise_complaint")
    suspend fun passengerAddRaiseComplaintApi(@Header("Authorization") token : String,
                                           @Field("booking_id") booking_id : String,
                                           @Field("com_message") com_message : String
    ) : Response<PassengerAddRaiseComplaintResponseModel>

    //  @FormUrlEncoded
    @GET("passenenger_raise_complaint_list")
    suspend fun passengerComplaintListApi(@Header("Authorization") token : String
    ) : Response<PassengerComplaintListResponseModel>

    @FormUrlEncoded
    @POST("passenenger_raise_complaint_list_details")
    suspend fun passengerComplaintListDetailApi(@Header("Authorization") token : String,
                                             @Field("booking_id") booking_id : String
    ) : Response<PassengerComplaintListDetailResponseModel>

    //  @FormUrlEncoded
    @GET("get_my_offer")
    suspend fun getMyOffersApi(@Header("Authorization") token : String
    ) : Response<MyOffersResponseModel>

    @FormUrlEncoded
    @POST("search_authorized_franchises")
    suspend fun searchAuthorizedFranchisesApi(@Header("Authorization") token : String,
                                                @Field("state_id") state_id : String,
                                                @Field("district_id") district_id : String,
                                                @Field("pin_code") pin_code : String
    ) : Response<SearchAuthorisedFranchisesResponseModel>

    @FormUrlEncoded
    @POST("send_user__loader_invoice")
    suspend fun sendMailLoaderInvoiceApi(@Header("Authorization") token : String,
                                              @Field("booking_id") state_id : String
    ) : Response<LoaderSendMailResponseModel>

    @FormUrlEncoded
    @POST("send_user__passenenger_invoice")
    suspend fun sendMailPassengerInvoiceApi(@Header("Authorization") token : String,
                                        @Field("booking_id") state_id : String
    ) : Response<LoaderSendMailResponseModel>

    //  @FormUrlEncoded
    @GET("loader_cancel_booking")
    suspend fun loaderCancelledBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<CancelledLoaderTripHistoryResponseModel>

    //  @FormUrlEncoded
    @GET("passenenger_cancel_booking")
    suspend fun passengerCancelledBookingTripHistoryApi(@Header("Authorization") token : String
    ) : Response<CancelledPassengerTripHistoryResponseModel>

    @FormUrlEncoded
    @POST("passenenger_invoice_url")
    suspend fun passengerDownloadInvoiceUrlApi(@Header("Authorization") token : String, @Field("booking_id") booking_id : String
    ) : Response<PassengerDownloadInvoiceUrlResponseModel>

    @FormUrlEncoded
    @POST("loader_invoice_url")
    suspend fun loaderDownloadInvoiceUrlApi(@Header("Authorization") token : String,
                                            @Field("booking_id") booking_id : String
     ) : Response<LoaderDownloadInvoiceUrlResponseModel>

    @FormUrlEncoded
    @POST("loader_invoice_url_second")
    suspend fun loader_invoice_url_second(@Header("Authorization") token : String,
                                            @Field("booking_id") booking_id : String
    ) : Response<LoaderDownloadInvoiceUrlResponseModel>

    @FormUrlEncoded
    @POST("passenenger_invoice_url_second")
    suspend fun passenenger_invoice_url_second(@Header("Authorization") token : String,
                                            @Field("booking_id") booking_id : String
    ) : Response<LoaderDownloadInvoiceUrlResponseModel>

    @GET("state_list")
    suspend fun getAuthorisedFranchisesStateListApi(@Header("Authorization") token : String): Response<AuthorisedFranchisesStateListResponseModel>

    @FormUrlEncoded
    @POST("city_list")
    suspend fun getAuthorisedFranchisesDisttListApi(@Header("Authorization") token : String,
                                            @Field("state_id") state_id : String
    ) : Response<AuthorisedFranchisesDisttListResponseModel>

    @FormUrlEncoded
    @POST("pincode_list")
    suspend fun getAuthorisedFranchisesPincodeListApi(@Header("Authorization") token : String,
                                            @Field("city_id") city_id : String
    ) : Response<AuthorisedFranchisesPinCodeListResponseModel>
    @FormUrlEncoded
    @POST("vendor_number_vehicle_list")
    suspend fun vendor_number_vehicle_list(@Header("Authorization") token : String,
                                            @Field("id") id : String
    ) : Response<AuthorizedFranchiseDetailsApi>

    @FormUrlEncoded
    @POST("whatsapp_status")
    suspend fun settingsWhatsappUpdatesApi(@Header("Authorization") token : String,
                                            @Field("status") state_id : String
    ) : Response<SettingWhatsappResponseModel>

    @FormUrlEncoded
    @POST("sms_email_status")
    suspend fun settingsSmsEmailUpdatesApi(@Header("Authorization") token : String,
                                           @Field("status") state_id : String
    ) : Response<SettingSmsEmailResponseModel>

    @FormUrlEncoded
    @POST("user_wallet_payment")
    suspend fun my_wallet_payment(
        @Header("Authorization") authorization: String,
        @Field("type") type:String,
        @Field("transaction_id") transaction_id:String,
        @Field("amount") amount:String,
    ): Response<LoaderAddWalletResponseModel>

}
