package org.wojo.wojosToolbelt;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.io.adapter.PacketAdapters;
import com.hypixel.hytale.server.core.io.adapter.PacketFilter;
import com.hypixel.hytale.server.core.io.adapter.PacketWatcher;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.wojo.wojosToolbelt.Commands.WojosToolbeltCommandCollection;
import org.wojo.wojosToolbelt.HotbarAdapter.HotbarToolbeltPacketAdapter;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class WojosToolbeltPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private PacketFilter inboundWatcher;

    public WojosToolbeltPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.getCommandRegistry().registerCommand(new WojosToolbeltCommandCollection());

        HotbarToolbeltPacketAdapter handler = new HotbarToolbeltPacketAdapter();
        this.inboundWatcher = PacketAdapters.registerInbound(handler);
    }

    @Override
    protected void shutdown() {
        if (this.inboundWatcher != null) {
            PacketAdapters.deregisterInbound(this.inboundWatcher);
        }
    }
}