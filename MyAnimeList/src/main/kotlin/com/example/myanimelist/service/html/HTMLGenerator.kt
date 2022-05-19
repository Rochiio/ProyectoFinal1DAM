package com.example.myanimelist.service.html

import com.example.myanimelist.dto.BackupDTO
import kotlinx.html.body
import kotlinx.html.dom.create
import kotlinx.html.head
import kotlinx.html.html
import javax.xml.parsers.DocumentBuilderFactory

//TODO terminar
class HTMLGenerator : IHTMLGenerator<BackupDTO> {
    override fun generate(dto: BackupDTO) {
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
        document.create.html {
            head { +"Datos" }
            body { }
        }
    }
}