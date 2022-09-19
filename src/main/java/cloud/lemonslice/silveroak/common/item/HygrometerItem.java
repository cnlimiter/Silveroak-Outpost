package cloud.lemonslice.silveroak.common.item;

import cloud.lemonslice.silveroak.client.ClientEnvironmentDataHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HygrometerItem extends NormalItem
{
    public HygrometerItem()
    {
        super(new Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pLevel != null && pLevel.isClientSide)
        {
            pTooltipComponents.add(ClientEnvironmentDataHandler.getHumidityInfo());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide())
        {
            pPlayer.displayClientMessage(ClientEnvironmentDataHandler.getHumidityInfo(), true);
        }
        return InteractionResultHolder.success(itemStack);
    }
}
