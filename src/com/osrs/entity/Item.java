package com.osrs.entity;

import com.osrs.cache.def.ItemDefinition;
import com.osrs.entity.model.Model;

public final class Item extends Renderable {

    public int ID;
    public int x;
    public int y;
    public int itemCount;

    public final Model getRotatedModel() {
        ItemDefinition itemDef = ItemDefinition.lookup(ID);
        return itemDef.getModel(itemCount);
    }
}
