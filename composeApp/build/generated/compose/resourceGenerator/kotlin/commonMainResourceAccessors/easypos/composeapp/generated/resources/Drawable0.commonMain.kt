@file:OptIn(InternalResourceApi::class)

package easypos.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/easypos.composeapp.generated.resources/"

internal val Res.drawable.burguer1: DrawableResource by lazy {
      DrawableResource("drawable:burguer1", setOf(
        ResourceItem(setOf(), "${MD}drawable/burguer1.png", -1, -1),
      ))
    }

internal val Res.drawable.burguer2: DrawableResource by lazy {
      DrawableResource("drawable:burguer2", setOf(
        ResourceItem(setOf(), "${MD}drawable/burguer2.png", -1, -1),
      ))
    }

internal val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

internal val Res.drawable.fallback: DrawableResource by lazy {
      DrawableResource("drawable:fallback", setOf(
        ResourceItem(setOf(), "${MD}drawable/fallback.png", -1, -1),
      ))
    }

internal val Res.drawable.refri1: DrawableResource by lazy {
      DrawableResource("drawable:refri1", setOf(
        ResourceItem(setOf(), "${MD}drawable/refri1.png", -1, -1),
      ))
    }

internal val Res.drawable.sorvete1: DrawableResource by lazy {
      DrawableResource("drawable:sorvete1", setOf(
        ResourceItem(setOf(), "${MD}drawable/sorvete1.png", -1, -1),
      ))
    }

internal val Res.drawable.suco1: DrawableResource by lazy {
      DrawableResource("drawable:suco1", setOf(
        ResourceItem(setOf(), "${MD}drawable/suco1.png", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("burguer1", Res.drawable.burguer1)
  map.put("burguer2", Res.drawable.burguer2)
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("fallback", Res.drawable.fallback)
  map.put("refri1", Res.drawable.refri1)
  map.put("sorvete1", Res.drawable.sorvete1)
  map.put("suco1", Res.drawable.suco1)
}
