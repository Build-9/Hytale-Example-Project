// This system happens on every tick. 
// It checks every player with a QuickAccess component 
// - Makes sure a quickaccess item is stored in the needed hotbar slot
// - If no item in inventory exists, remove component from player
// - Check if item in hotbar matches saved value in compoenent. 
//    If not: Update the component info
//    else: do nothing
public class QuickAccessEntityTickingSystem extends DelayedEntitySystem<EntityStore> {
  public HealthRegenSystem() {
    super(1.0f); // Don't need super rapid updates (TODO: Update this value)
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
