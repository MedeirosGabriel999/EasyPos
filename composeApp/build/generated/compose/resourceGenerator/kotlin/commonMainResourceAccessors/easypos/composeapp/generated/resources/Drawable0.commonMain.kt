@file:OptIn(InternalResourceApi::class)

package easypos.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/easypos.composeapp.generated.resources/"

internal val Res.drawable.banner1: DrawableResource by lazy {
      DrawableResource("drawable:banner1", setOf(
        ResourceItem(setOf(), "${MD}drawable/banner1.png", -1, -1),
      ))
    }

internal val Res.drawable.banner2: DrawableResource by lazy {
      DrawableResource("drawable:banner2", setOf(
        ResourceItem(setOf(), "${MD}drawable/banner2.png", -1, -1),
      ))
    }

internal val Res.drawable.banner3: DrawableResource by lazy {
      DrawableResource("drawable:banner3", setOf(
        ResourceItem(setOf(), "${MD}drawable/banner3.png", -1, -1),
      ))
    }

internal val Res.drawable.banner4: DrawableResource by lazy {
      DrawableResource("drawable:banner4", setOf(
        ResourceItem(setOf(), "${MD}drawable/banner4.png", -1, -1),
      ))
    }

internal val Res.drawable.banners: DrawableResource by lazy {
      DrawableResource("drawable:banners", setOf(
        ResourceItem(setOf(), "${MD}drawable/banners", -1, -1),
      ))
    }

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
  map.put("banner1", Res.drawable.banner1)
  map.put("banner2", Res.drawable.banner2)
  map.put("banner3", Res.drawable.banner3)
  map.put("banner4", Res.drawable.banner4)
  map.put("banners", Res.drawable.banners)
  map.put("burguer1", Res.drawable.burguer1)
  map.put("burguer2", Res.drawable.burguer2)
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("fallback", Res.drawable.fallback)
  map.put("refri1", Res.drawable.refri1)
  map.put("sorvete1", Res.drawable.sorvete1)
  map.put("suco1", Res.drawable.suco1)
}
