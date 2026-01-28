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
public class WojosQuickAccessPlugin extends JavaPlugin {
    private static WojosQuickAccessPlugin instance = null;
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    
    private PacketFilter _inbound_filter;

    public WojosToolbeltPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    private void registerComponents(){
    }
    private void registerSystems(){
    }
    private void registerEvents(){
    }
    private void registerCommands(){
        this.getCommandRegistry().registerCommand(new WojosToolbeltCommandCollection());
    }
    private void registerPacketAdapters(){
        this._inbound_hotbar_filter = PacketAdapters.registerInbound(new QuickAccessPacketAdapter());
    }
    
    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.registerComponents();
        this.registerSystems();
        this.registerEvents();
        this.registerCommands();
        this.registerPacketAdapters();
    }

    @Override
    protected void shutdown() {
        if (this._inbound_hotbar_filter != null) {
            PacketAdapters.deregisterInbound(this._inbound_hotbar_filter);
        }
    }

    public static WojosQuickAccessPlugin get() {
        return instance
    }
}
