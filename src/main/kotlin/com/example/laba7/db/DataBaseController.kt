package com.example.laba7.db

import com.example.laba1.db.dataBaseLogin
import com.example.laba1.db.dataBasePassword
import com.example.laba1.db.urlConnect
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class DataBaseController {

    private var connection: Connection? = null

    init {


        try {
            connection = DriverManager.getConnection(urlConnect, dataBaseLogin, dataBasePassword)


        } catch (ignored: SQLException) {
            println("failed ${ignored.toString()}")
        }
    }

    fun addUnit(name: String, code: String): String {
        val positionCode = getPositionCode(code)
        val task = "insert into unit(name, code) values('$name', '$positionCode')"
        sendStatement(task, false)

        return positionCode
    }

    private fun getPositionCode(code: String): String {
        val task = "select * from unit where code like '$code%'"
        val resultSet = sendStatement(task, true) as ResultSet
        var i = 0
        while (resultSet.next()) {
            i++
        }

        return "$code$i"
    }


    @Throws(SQLException::class)
    fun sendStatement(task: String, isSelecting: Boolean): Any {
        val statement = connection!!.createStatement()
        return if (isSelecting) statement.executeQuery(task) else statement.executeUpdate(task)
    }

}