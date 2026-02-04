package org.wojo.wojosToolbelt;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.io.adapter.PacketAdapters;
import com.hypixel.hytale.server.core.io.adapter.PacketFilter;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Commands.ComponentCommands.AddQuickAccessComponentToPlayerCommand;
import org.wojo.wojosToolbelt.Commands.ComponentCommands.PrintQuickAccessComponentInfo;
import org.wojo.wojosToolbelt.Commands.ComponentCommands.RemoveQuickAccessComponentFromPlayerCommand;
import org.wojo.wojosToolbelt.Commands.ItemCommands.AddItemToComponentCommand;
import org.wojo.wojosToolbelt.Commands.ItemCommands.SwapItemsCommand;
import org.wojo.wojosToolbelt.Commands.WojosToolbeltCommandCollection;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Events.AddItemEvent;
import org.wojo.wojosToolbelt.Events.SwapItemEvent;
import org.wojo.wojosToolbelt.Handlers.AddItemHandler;
import org.wojo.wojosToolbelt.Handlers.SwapItemHandler;
import org.wojo.wojosToolbelt.Systems.QuickAccessEntityTickingSystem;
import org.wojo.wojosToolbelt.PacketAdapters.HotbarOpenQuickAccessGuiPacketAdapter;
import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class WojosQuickAccessPlugin extends JavaPlugin {
    private static WojosQuickAccessPlugin _instance = null;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    
    private PacketFilter _inbound_hotbar_filter;
    private ComponentType<EntityStore, QuickAccessComponent> _quick_access_component;

    // Threadsafe accessor to check if a given player has a QuickAccessComponent.
    //     Needed by network thread as getting the actual comp requires using the world thread. 
    public static ConcurrentHashMap<Ref<EntityStore>, Boolean> hasQuickAccessComponentMap = new ConcurrentHashMap<>();

    public WojosQuickAccessPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        _instance = this;
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    private void registerComponents(){
        var compType = this.getEntityStoreRegistry().registerComponent(QuickAccessComponent.class, QuickAccessComponent::new);
        QuickAccessComponent.setComponentType(compType);
    }
    private void registerSystems(){
        this.getEntityStoreRegistry().registerSystem(new QuickAccessEntityTickingSystem(this._quick_access_component));
    }
    private void registerEvents(){
        getEventRegistry().register(SwapItemEvent.class, new SwapItemHandler());
        getEventRegistry().register(AddItemEvent.class, new AddItemHandler());
    }
    private void registerCommands(){
        // TODO: Update the command collection to properly hold everything
        
        // Component Commands
        this.getCommandRegistry().registerCommand(new WojosToolbeltCommandCollection());
        this.getCommandRegistry().registerCommand(new AddQuickAccessComponentToPlayerCommand());
        this.getCommandRegistry().registerCommand(new RemoveQuickAccessComponentFromPlayerCommand());
        this.getCommandRegistry().registerCommand(new PrintQuickAccessComponentInfo());

        // Item Commands
        this.getCommandRegistry().registerCommand(new AddItemToComponentCommand());
        this.getCommandRegistry().registerCommand(new SwapItemsCommand());
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
