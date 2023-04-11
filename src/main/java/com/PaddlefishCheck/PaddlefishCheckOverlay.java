package com.PaddlefishCheck;

import net.runelite.api.Client;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import javax.inject.Inject;
import java.awt.*;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class PaddlefishCheckOverlay extends OverlayPanel {
    private final Client client;
    private final PaddlefishCheckConfig config;
    private final PaddlefishCheckPlugin plugin;
    private final InfoBoxManager infoBoxManager;
    private final ItemManager itemManager;
    @Inject
    PaddlefishCheckOverlay(
            Client client,
            PaddlefishCheckConfig config,
            PaddlefishCheckPlugin plugin,
            InfoBoxManager infoBoxManager,
            ItemManager itemManager)
    {
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setPriority(OverlayPriority.LOW);
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.infoBoxManager = infoBoxManager;
        this.itemManager = itemManager;
        addMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Paddlefish Check overlay");
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if(plugin.isInGauntlet() && plugin.hasRawPaddleFish())
        {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("YOU HAVE RAW PADDLEFISH").leftColor(Color.RED)
                    .build());
        }
        return super.render(graphics);
    }

}
