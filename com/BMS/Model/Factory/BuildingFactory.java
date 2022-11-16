package com.BMS.Model.Factory;

import com.BMS.Model.Building;

/**
 * The factory for Building.
 *
 * @see com.BMS.Model.Building
 */
public abstract class BuildingFactory {
    public abstract Building createBuilding();
}
