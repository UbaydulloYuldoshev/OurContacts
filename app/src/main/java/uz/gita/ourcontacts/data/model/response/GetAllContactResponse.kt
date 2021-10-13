package uz.gita.ourcontacts.data.model.response

import java.io.Serializable

data class GetAllContactResponse(
    val data: List<ContactData>,
    val message: String
)

data class ContactData(
    val id: Int,
    val phone: String,
    val name: String
) : Serializable