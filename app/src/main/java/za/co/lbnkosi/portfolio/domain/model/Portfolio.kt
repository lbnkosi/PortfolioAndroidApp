package za.co.lbnkosi.portfolio.domain.model

import com.google.gson.annotations.SerializedName

data class Portfolio(
    @SerializedName("user")
    var user: User = User(),

    @SerializedName("contacts")
    var contactList: ArrayList<Contact> = arrayListOf(),

    @SerializedName("addresses")
    var addressList: ArrayList<Address> = arrayListOf(),

    @SerializedName("employmentHistory")
    var workList: ArrayList<Work> = arrayListOf(),

    @SerializedName("education")
    var educationList: ArrayList<Education> = arrayListOf(),

    @SerializedName("skills")
    var skillsList: ArrayList<Skill> = arrayListOf(),

    @SerializedName("langauges")
    var languageList: ArrayList<Language> = arrayListOf(),

    @SerializedName("competencies")
    var competencyList: ArrayList<Competency> = arrayListOf(),

    @SerializedName("socials")
    var socialList: ArrayList<Social> = arrayListOf(),

    @SerializedName("projects")
    var projectList: ArrayList<Project> = arrayListOf()
)