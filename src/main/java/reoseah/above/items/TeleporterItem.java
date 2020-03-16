package reoseah.above.items;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import reoseah.above.Above;

public class TeleporterItem extends Item {
	public TeleporterItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClient) {
			FabricDimensions.teleport(player, player.world.dimension.getType() == Above.DIMENSION_TYPE
					? DimensionType.OVERWORLD
					: Above.DIMENSION_TYPE);
		}
		return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
	}
}