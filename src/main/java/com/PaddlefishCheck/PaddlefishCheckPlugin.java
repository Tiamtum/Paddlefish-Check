package com.PaddlefishCheck;

import com.google.inject.Provides;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;


@Slf4j
@PluginDescriptor(
		name = "Paddlefish Check",
		description = "Checks your inventory for raw paddlefish within The Gauntlet/Corrupted Gauntlet.",
		tags = {"the","gauntlet"},
		enabledByDefault = false
)

public class PaddlefishCheckPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private PaddlefishCheckConfig config;
	@Inject
	private PaddlefishCheckOverlay overlay;
	@Inject
	private OverlayManager overlayManager;
	private static final int VARBIT_MAZE = 9178; //Changes upon entering gauntlet/corrupted gauntlet
	public boolean isInGauntlet()
	{
		if(client.getLocalPlayer() == null)
		{
			return false;
		}
		return client.getVarbitValue(VARBIT_MAZE) == 1;
	}
	public boolean hasRawPaddleFish()
	{
		return client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.RAW_PADDLEFISH);

	}
	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}
	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}
	@Provides
	PaddlefishCheckConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PaddlefishCheckConfig.class);
	}
}
