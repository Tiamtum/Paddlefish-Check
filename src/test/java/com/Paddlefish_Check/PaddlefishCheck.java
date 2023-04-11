package com.Paddlefish_Check;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PaddlefishCheck
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PaddlefishCheckPlugin.class);
		RuneLite.main(args);
	}
}