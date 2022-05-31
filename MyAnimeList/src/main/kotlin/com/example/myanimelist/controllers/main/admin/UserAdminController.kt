package com.example.myanimelist.controllers.main.admin

import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.extensions.show
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.views.models.UserView
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import org.apache.logging.log4j.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserAdminController : KoinComponent {
    private val logger: Logger = getLogger<UserAdminController>()
    private val usersRepository: IUsersRepository by inject()
    private var listUser: ObservableList<UserView> = FXCollections.observableArrayList()

    @FXML
    lateinit var tabla: TableView<UserView>

    @FXML
    lateinit var columnName: TableColumn<UserView, String>

    @FXML
    lateinit var columnEmail: TableColumn<UserView, String>

    @FXML
    lateinit var txtName: Label

    @FXML
    lateinit var txtEmail: Label

    @FXML
    lateinit var txtBirthDay: Label

    @FXML
    fun initialize() {
        loadData()
        initCells()
        initListener()
        clearUser()
        tabla.selectionModel.selectFirst()
    }

    private fun clearUser() {
        txtName.text = " "
        txtEmail.text = " "
        txtBirthDay.text = " "
    }

    private fun setUser(user: UserView) {
        txtName.text = user.getName()
        txtEmail.text = user.getEmail()
        txtBirthDay.text = user.getBirthDate().toString()
    }


    private fun initListener() {
        tabla.selectionModel.selectedItemProperty()
            .addListener { _: ObservableValue<out UserView?>, _: UserView?, newValue: UserView? ->
                selectUser(newValue)
            }
    }

    private fun selectUser(newValue: UserView?) {
        if (newValue == null) {
            clearUser()
        } else {
            setUser(newValue)
        }
    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        val lista: List<UserView> = usersRepository.findAll().filter { a -> !a.admin }.map { UserView(it) }.toList()
        listUser.addAll(lista)
    }

    private fun initCells() {
        tabla.items = listUser
        columnName.setCellValueFactory { it.value.nameProperty() }
        columnEmail.setCellValueFactory { it.value.emailProperty() }
    }


//    fun changeSceneToProfileUser(mouseEvent: MouseEvent) {
//        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
//            val user: UserView = tabla.selectionModel.selectedItem
//            DependenciesManager.userSelectionAdmin = user
//            Stage().loadScene(PERFIL_VIEW_ADMIN, WIDTH, HEIGHT) {
//                title = "User profile"
//                isResizable = false
//                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
//            }.show()
//
//        }
//    }


    /**
     * Eliminar al usuario seleccionado
     */
    fun deleteUser(actionEvent: ActionEvent) {
        val item = tabla.selectionModel.selectedItem
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.headerText = "Eliminar"
        alert.contentText = "Desea eliminar al usuario ${item.getName()}"
        val result = alert.showAndWait()
        if (result.get() == ButtonType.OK) {
            usersRepository.delete(item.id)
            listUser.remove(item)
            Alert(Alert.AlertType.INFORMATION).show("Eliminar", "Usuario eliminado correctamente")
        }
    }


}