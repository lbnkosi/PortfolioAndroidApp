package za.co.lbnkosi.portfolio.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import za.co.lbnkosi.portfolio.domain.model.*
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import za.co.lbnkosi.portfolio.presentation.common.Constants
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: PortfolioUseCase) : ViewModel() {

    val portfolio: LiveData<Portfolio?>
        get() = _portfolio

    val address: LiveData<ArrayList<Address>?>
        get() = _addresses

    val competency: LiveData<ArrayList<Competency>?>
        get() = _competencies

    val contact: LiveData<ArrayList<Contact>?>
        get() = _contacts

    val education: LiveData<ArrayList<Education>?>
        get() = _education

    val language: LiveData<ArrayList<Language>?>
        get() = _languages

    val project: LiveData<ArrayList<Project>?>
        get() = _projects

    val skill: LiveData<ArrayList<Skill>?>
        get() = _skills

    val social: LiveData<ArrayList<Social>?>
        get() = _socials

    val user: LiveData<User?>
        get() = _user

    val work: LiveData<ArrayList<Work>?>
        get() = _work

    private var _portfolio: MutableLiveData<Portfolio?> = MutableLiveData(Portfolio())

    private var _addresses: MutableLiveData<ArrayList<Address>?> = MutableLiveData(arrayListOf())

    private var _competencies: MutableLiveData<ArrayList<Competency>?> = MutableLiveData(arrayListOf())

    private var _contacts: MutableLiveData<ArrayList<Contact>?> = MutableLiveData(arrayListOf())

    private var _education: MutableLiveData<ArrayList<Education>?> = MutableLiveData(arrayListOf())

    private var _languages: MutableLiveData<ArrayList<Language>?> = MutableLiveData(arrayListOf())

    private var _projects: MutableLiveData<ArrayList<Project>?> = MutableLiveData(arrayListOf())

    private var _skills: MutableLiveData<ArrayList<Skill>?> = MutableLiveData(arrayListOf())

    private var _socials: MutableLiveData<ArrayList<Social>?> = MutableLiveData(arrayListOf())

    private var _user: MutableLiveData<User?> = MutableLiveData(User())

    private var _work: MutableLiveData<ArrayList<Work>?> = MutableLiveData(arrayListOf())

    fun fetchPortfolio() {
        viewModelScope.launch {
            useCase.getPortfolio(Constants.KEY, Constants.UID).collect {
                _portfolio.value = it.data
            }
        }
    }

    fun fetchAddresses() {
        viewModelScope.launch {
            useCase.fetchAddress(Constants.KEY, Constants.UID).collect {
                _addresses.value = it.data
            }
        }
    }

    fun fetchCompetencies() {
        viewModelScope.launch {
            useCase.fetchCompetencies(Constants.KEY, Constants.UID).collect {
                _competencies.value = it.data
            }
        }
    }

    fun fetchContacts() {
        viewModelScope.launch {
            useCase.fetchContacts(Constants.KEY, Constants.UID).collect {
                _contacts.value = it.data
            }
        }
    }

    fun fetchEducation() {
        viewModelScope.launch {
            useCase.fetchEducation(Constants.KEY, Constants.UID).collect {
                _education.value = it.data
            }
        }
    }

    fun fetchLanguages() {
        viewModelScope.launch {
            useCase.fetchLanguage(Constants.KEY, Constants.UID).collect {
                _languages.value = it.data
            }
        }
    }

    fun fetchProjects() {
        viewModelScope.launch {
            useCase.fetchProjects(Constants.KEY, Constants.UID).collect {
                _projects.value = it.data
            }
        }
    }

    fun fetchSkills() {
        viewModelScope.launch {
            useCase.fetchSkills(Constants.KEY, Constants.UID).collect {
                _skills.value = it.data
            }
        }
    }

    fun fetchSocials() {
        viewModelScope.launch {
            useCase.fetchSocials(Constants.KEY, Constants.UID).collect {
                _socials.value = it.data
            }
        }
    }

    fun fetchUser() {
        viewModelScope.launch {
            useCase.fetchUser(Constants.KEY, Constants.UID).collect {
                _user.value = it.data
            }
        }
    }

    fun fetchWork() {
        viewModelScope.launch {
            useCase.fetchWork(Constants.KEY, Constants.UID).collect {
                _work.value = it.data
            }
        }
    }

}