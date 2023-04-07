package com.example.laba7

import com.example.laba7.db.DataBaseController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField

class Controller {

    val db = DataBaseController()

    @FXML
    private lateinit var code: TextField

    @FXML
    private lateinit var label: Label

    @FXML
    private lateinit var name: TextField


    @FXML
    fun onSendButtonClick(event: ActionEvent) {
        val nameText = name.text
        val codeText = code.text

        val positionCode = db.addUnit(nameText, codeText)

        label.text = "Код внутреннего учета: $positionCode"

        name.clear()
        code.clear()



    }



}