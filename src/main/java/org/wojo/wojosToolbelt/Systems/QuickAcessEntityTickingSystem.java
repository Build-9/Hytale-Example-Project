// This system happens on every tick. 
// It checks every player with a QuickAccess component 
// - Makes sure a quickaccess item is stored in the needed hotbar slot
// - If no item in inventory exists, remove component from player
// - Check if item in hotbar matches saved value in compoenent. 
//    If not: Update the component info
//    else: do nothing
