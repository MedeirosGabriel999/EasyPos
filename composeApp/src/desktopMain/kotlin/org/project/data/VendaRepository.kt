@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.project.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.project.models.Venda
import java.io.File


actual object VendasRepository {
    private val arquivoVendas = File("vendas.json")
    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    init {
        if (!arquivoVendas.exists()) {
            arquivoVendas.createNewFile()
            arquivoVendas.writeText("[]")
        }
    }

    actual fun salvarVenda(venda: Venda) {
        val vendasAtuais = lerVendas().toMutableList()
        vendasAtuais.add(venda)
        val jsonString = json.encodeToString(vendasAtuais)
        arquivoVendas.writeText(jsonString)
    }

    actual fun lerVendas(): List<Venda> {
        val jsonString = arquivoVendas.readText()
        if (jsonString.isBlank() || jsonString == "[]") return emptyList()
        return json.decodeFromString(jsonString)
    }
}