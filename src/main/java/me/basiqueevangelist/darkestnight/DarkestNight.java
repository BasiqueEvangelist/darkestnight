package me.basiqueevangelist.darkestnight;

import me.basiqueevangelist.darkestnight.items.LightCheckTool;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;


public class DarkestNight implements ModInitializer {
    @Override
    public void onInitialize() {
        LogManager.getLogger("DarkestNight").info("The night approaches..");

        Registry.register(Registry.ITEM, new Identifier("darkestnight", "light_check_tool"), new LightCheckTool(new Item.Settings().maxCount(1)));
    }
}
