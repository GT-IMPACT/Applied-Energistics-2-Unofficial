package appeng.block;

import appeng.core.localization.GuiText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import java.util.List;

public class AEBaseItemBlockQuantumBase extends AEBaseItemBlock
{

	public AEBaseItemBlockQuantumBase(final Block id )
	{
		super( id );
	}

	@Override
	@SideOnly( Side.CLIENT )
	public void addCheckedInformation( final ItemStack itemStack, final EntityPlayer player, final List<String> toolTip, final boolean advancedTooltips )
	{
		toolTip.add( GuiText.EnergyDrain.getLocal() + ": " + 4096 + " EU/t");
	}
}
