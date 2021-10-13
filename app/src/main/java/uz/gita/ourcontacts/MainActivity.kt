package uz.gita.ourcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.ourcontacts.data.ApiClient
import uz.gita.ourcontacts.data.api.OurContactApi
import uz.gita.ourcontacts.data.model.request.AddContactRequest
import uz.gita.ourcontacts.data.model.response.AddContactResponse
import uz.gita.ourcontacts.data.model.response.ContactData
import uz.gita.ourcontacts.data.model.response.GetAllContactResponse
import uz.gita.ourcontacts.ui.adapter.OurContactAdapter
import uz.gita.ourcontacts.ui.dialog.AddContactDialog
import uz.gita.ourcontacts.ui.dialog.EditContactDialog
import uz.gita.ourcontacts.ui.dialog.EventDialog


class MainActivity : AppCompatActivity() {
    private val list = ArrayList<ContactData>()
    private val api : OurContactApi = ApiClient.retrofit.create(OurContactApi::class.java)
    private val adapter = OurContactAdapter()
    private val refresh by lazy { findViewById<SwipeRefreshLayout>(R.id.refresh) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.contactList)
        val addButton = findViewById<Button>(R.id.addButton)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        getContactData()

        refresh.setOnRefreshListener {
            getContactData()
        }

        adapter.clickListener = { pos,id ->
            val changeDialog = EventDialog()
            changeDialog.setDeleteListener {
                delete(id)
            }
            changeDialog.setEditListener {
                val editDialog = EditContactDialog()
                val bundle = Bundle()
                bundle.putSerializable("data",list[pos])
                editDialog.arguments = bundle
                editDialog.setListener {
                    update(it,pos)
                }
                editDialog.show(supportFragmentManager,"edit")
            }
            changeDialog.show(supportFragmentManager,"change")
        }
        addButton.setOnClickListener {
            val dialog = AddContactDialog()
            dialog.setListener {
                insertContact(it)
            }
            dialog.show(supportFragmentManager,"add")
        }
    }

    private fun getContactData() {
        api.getAllContactList().enqueue(object : Callback<GetAllContactResponse> {
            override fun onResponse(call: Call<GetAllContactResponse>, response: Response<GetAllContactResponse>) {
                response.body()?.let {
                    list.clear()
                    list.addAll(it.data)
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<GetAllContactResponse>, t: Throwable) {
                refresh.isRefreshing = false
            }
        })
    }

    private fun insertContact(data : AddContactRequest) {
        api.addContact(data).enqueue(object : Callback<AddContactResponse> {
            override fun onResponse(call: Call<AddContactResponse>, response: Response<AddContactResponse>) {
                response.body()?.let {
                    list.add(it.data)
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<AddContactResponse>, t: Throwable) {
            }

        })
    }

    private fun update(data : ContactData,pos : Int) {
        api.editContact(data).enqueue(object  : Callback<AddContactResponse> {
            override fun onResponse(call: Call<AddContactResponse>, response: Response<AddContactResponse>) {
                response.body()?.let {
                    list[pos] = it.data
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<AddContactResponse>, t: Throwable) {
            }
        })
    }

    private fun delete(id : Int) {
        api.deleteContact(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                list.removeAt(id)
//                adapter.submitList(list)
//                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
            }
        })
    }
}






