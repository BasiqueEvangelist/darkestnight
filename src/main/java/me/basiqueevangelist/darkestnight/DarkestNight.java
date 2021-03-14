package me.basiqueevangelist.darkestnight;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;


public class DarkestNight implements ModInitializer {
    @Override
    public void onInitialize() {
        LogManager.getLogger("DarkestNight").info("The night approaches..");
    }
}
