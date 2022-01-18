package za.co.lbnkosi.portfolio.domain.model

data class Portfolio(
    var user: User = User(),
    var contactList: ArrayList<Contact> = arrayListOf(),
    var addressList: ArrayList<Address> = arrayListOf(),
    var workList: ArrayList<Work> = arrayListOf(),
    var educationList: ArrayList<Education> = arrayListOf(),
    var skillsList: ArrayList<Skill> = arrayListOf(),
    var languageList: ArrayList<Language> = arrayListOf(),
    var competencyList: ArrayList<Competency> = arrayListOf(),
    var socialList: ArrayList<Social> = arrayListOf(),
    var projectList: ArrayList<Project> = arrayListOf()
)