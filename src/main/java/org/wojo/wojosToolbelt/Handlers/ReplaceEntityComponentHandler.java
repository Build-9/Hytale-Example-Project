public class ReplaceEntityComponentHandler implements Consumer<ReplaceEntityComponentEvent> {
    @Override
    public void accept(ReplaceEntityComponentEvent event) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Replace Entity Component Event) - A Replace-Entity-Component-Event was triggered!");
    
        Ref<EntityStore> entity =  event.getRefrence();
        QuickAccessComponent newComp = event.getQuickAccessComponent();
        if (entity != null && newComp != null){
          Store<EntityStore> store = entity.getStore();
          if (store != null){
            QuickAccessComponent qaComp = store.getComponent(entity, QuickAccessComponent.getComponentType());
            if (qaComp != null) {
              store.replaceComponent(entity, QuickAccessComponent.getComponentType(), newComp);
              WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Replace Entity Component Event) - QuickAccess Component has been replaced on entity"+entity.getId());
            }
          }
        }
    }
}
