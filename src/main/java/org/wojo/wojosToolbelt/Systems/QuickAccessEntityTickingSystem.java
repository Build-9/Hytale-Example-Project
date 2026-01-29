// This system happens on every tick. 
// It checks every player with a QuickAccess component 
// - Makes sure a quickaccess item is stored in the needed hotbar slot
// - If no item in inventory exists, remove component from player
// - Check if item in hotbar matches saved value in compoenent. 
//    If not: Update the component info
//    else: do nothing
package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;

import javax.annotation.Nonnull;

public class QuickAccessEntityTickingSystem extends DelayedEntitySystem<EntityStore> {

  private final ComponentType<EntityStore, QuickAccessComponent> quickAccessComponentType;

  public QuickAccessEntityTickingSystem(ComponentType<EntityStore, QuickAccessComponent> quickAccessComponentType) {
    super(10.0f); // Don't need super rapid updates (TODO: Update this value)
    this.quickAccessComponentType = quickAccessComponentType;
  }


  @Override
  public void tick(float dt, int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
                   @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
      // Runs every 1 second per matching entity
  }
  
  @Nonnull
  @Override
  public Query<EntityStore> getQuery() {
    return Query.and(QuickAccessComponent.getComponentType());
  }
}
