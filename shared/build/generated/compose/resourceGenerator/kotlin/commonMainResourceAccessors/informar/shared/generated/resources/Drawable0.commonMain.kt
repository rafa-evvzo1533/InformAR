@file:OptIn(InternalResourceApi::class)

package informar.shared.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceContentHash
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/informar.shared.generated.resources/"

@delegate:ResourceContentHash(181_725_734)
internal val Res.drawable.logoo: DrawableResource by lazy {
      DrawableResource("drawable:logoo", setOf(
        ResourceItem(setOf(), "${MD}drawable/logoo.png", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("logoo", Res.drawable.logoo)
}
