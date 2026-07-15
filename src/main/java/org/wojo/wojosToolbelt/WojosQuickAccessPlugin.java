package org.wojo.wojosToolbelt;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.io.adapter.PacketAdapters;
import com.hypixel.hytale.server.core.io.adapter.PacketFilter;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.wojo.wojosToolbelt.Commands.WojosQuickAccessCommandCollection;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.Events.SwapQuickAccessItemEvent;
import org.wojo.wojosToolbelt.Handlers.PlayerReadyEventHandler;
import org.wojo.wojosToolbelt.Handlers.SwapQuickAccessItemEventHandler;
import org.wojo.wojosToolbelt.Interactions.OpenQuickAccessSelectionGuiInteraction;
import org.wojo.wojosToolbelt.PacketAdapters.HotbarOpenQuickAccessGuiPacketAdapter;
import org.wojo.wojosToolbelt.Systems.QuickAccessPlayerComponentSystem;
import org.wojo.wojosToolbelt.Systems.QuickAccessPlayerSystem;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class WojosQuickAccessPlugin extends JavaPlugin {
    private static WojosQuickAccessPlugin _instance = null;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    
    private PacketFilter _inbound_hotbar_filter;

    public WojosQuickAccessPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        _instance = this;
        LOGGER.atInfo().log("[Info] Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    private void registerComponents(){
        var plyrCompType = this.getEntityStoreRegistry().registerComponent(QuickAccessPlayerComponent.class, QuickAccessPlayerComponent.QUICK_ACCESS_PLAYER_COMPONENT_ID, QuickAccessPlayerComponent.CODEC);
        QuickAccessPlayerComponent.setComponentType(plyrCompType);
    }
    
    private void registerSystems(){
        this.getEntityStoreRegistry().registerSystem(new QuickAccessPlayerComponentSystem(QuickAccessPlayerComponent.getComponentType()));
        this.getEntityStoreRegistry().registerSystem(new QuickAccessPlayerSystem(Player.getComponentType()));
    }

    private void registerEvents(){
        getEventRegistry().register(SwapQuickAccessItemEvent.class, new SwapQuickAccessItemEventHandler());
        getEventRegistry().registerGlobal(PlayerReadyEvent.class, PlayerReadyEventHandler::handle);
    }

    private void registerInteractions(){
        this.getCodecRegistry(Interaction.CODEC).register(OpenQuickAccessSelectionGuiInteraction.OpenQuickAccessSelectionGuiInteractionID, OpenQuickAccessSelectionGuiInteraction.class, OpenQuickAccessSelectionGuiInteraction.CODEC);
    }
    private void registerCommands(){
        this.getCommandRegistry().registerCommand(new WojosQuickAccessCommandCollection());
    }
    private void registerPacketAdapters(){
        this._inbound_hotbar_filter = PacketAdapters.registerInbound(new HotbarOpenQuickAccessGuiPacketAdapter());
    }
    
    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.registerComponents();
        this.registerSystems();
        this.registerEvents();
        this.registerInteractions();
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
        return _instance;
    }
}
