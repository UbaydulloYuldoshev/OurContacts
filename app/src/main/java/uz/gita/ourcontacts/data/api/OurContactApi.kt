package uz.gita.ourcontacts.data.api

import retrofit2.Call
import retrofit2.http.*
import uz.gita.ourcontacts.data.model.request.AddContactRequest
import uz.gita.ourcontacts.data.model.response.AddContactResponse
import uz.gita.ourcontacts.data.model.response.ContactData
import uz.gita.ourcontacts.data.model.response.GetAllContactResponse

interface OurContactApi {

    @GET("contact")
    fun getAllContactList() : Call<GetAllContactResponse>

    @POST("contact")
    fun addContact(@Body data : AddContactRequest) : Call<AddContactResponse>

    @PUT("contact")
    @Headers("Content-Type: application/json")
    fun editContact(@Body data : ContactData) : Call<AddContactResponse>

    @DELETE("contact")
    fun deleteContact(@Query("id") id : Int) : Call<Unit>
}