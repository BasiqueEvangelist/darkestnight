package me.basiqueevangelist.darkestnight.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class LightCheckTool extends Item {
    public LightCheckTool(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient())
            return ActionResult.SUCCESS;

        BlockPos neededPos = context.getBlockPos().add(context.getSide().getOffsetX(), context.getSide().getOffsetY(), context.getSide().getOffsetZ());
        context.getPlayer().sendMessage(new LiteralText("Light level is " + context.getWorld().getLightLevel(neededPos)), false);
        return ActionResult.SUCCESS;
    }
}
