package com.example.myanimelist.controllers.main.admin

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.PERFIL_VIEW_ADMIN
import com.example.myanimelist.utils.WIDTH
import com.example.myanimelist.views.models.UserView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.image.Image
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import org.apache.logging.log4j.Logger

class UserAdminController {
    private var logger: Logger = DependenciesManager.getLogger(UserAdminController::class.java)
    private var usersRepository: IUsersRepository = DependenciesManager.getUsersRepo()
    private var listUser: ObservableList<UserView> = FXCollections.observableArrayList()

    @FXML
    lateinit var tabla: TableView<UserView>

    @FXML
    lateinit var columnName: TableColumn<UserView, String>

    @FXML
    lateinit var columnEmail: TableColumn<UserView, String>

    @FXML
    fun initialize() {
        loadData()
        initCells()
    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        val lista: List<UserView> = usersRepository.findAll().filter { a -> !a.admin }.map { UserView(it) }.toList()
        listUser.addAll(lista)
    }

    private fun initCells() {
        tabla.items = listUser
        columnName.setCellValueFactory { cellData -> cellData.value.nameProperty() }
        columnEmail.setCellValueFactory { cellData -> cellData.value.emailProperty() }
    }


    fun changeSceneToProfileUser(mouseEvent: MouseEvent) {
        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            val user: UserView = tabla.selectionModel.selectedItem
            DependenciesManager.userSelectionAdmin = user
            Stage().loadScene(PERFIL_VIEW_ADMIN, WIDTH, HEIGHT) {
                title = "User profile"
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }.show()

        }
    }

    fun changeToAddUserView() {

    }

    fun addUser(actionEvent: ActionEvent) {

    }

    fun editUser(actionEvent: ActionEvent) {

    }

    fun deleteUser(actionEvent: ActionEvent) {

    }

}