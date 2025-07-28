@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.project.data

import org.project.models.Venda


expect object VendasRepository {
    fun salvarVenda(venda: Venda)
    fun lerVendas(): List<Venda>
}