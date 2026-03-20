public class QuickAccessPlayerSystem extends RefChangeSystem<EntityStore, QuickAccessPlayerComponent> {
    
    // When a player is added to the entity store, Add a quickAccess component to them if they dont already have it.
    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref, 
        @Nonnull QuickAccessPlayerComponent component, 
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) 
        {
        
        // Player component was added to a new entity
        QuickAccessPlayerComponent basicQuickAccessPlayerComponent = new QuickAccessPlayerComponent();
        store.setComponent(ref, basicQuickAccessPlayerComponent);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.not(QuickAccessPlayerComponent.getComponentType());
    }
}