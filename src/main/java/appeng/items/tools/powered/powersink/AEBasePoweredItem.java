/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.items.tools.powered.powersink;


import com.google.common.base.Optional;


public abstract class AEBasePoweredItem extends RedstoneFlux
{
	public AEBasePoweredItem( final double powerCapacity, final Optional<String> subName )
	{
		super( powerCapacity, subName );

		this.setMaxStackSize( 1 );
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer player) {
		//chargeFromArmor(stack, player);
		return super.onItemRightClick(stack, w, player);
	}

	public void chargeFromArmor(ItemStack aStack, EntityPlayer aPlayer) {

		ItemStack tArmor = aPlayer.getCurrentArmor(2);
		if (tArmor != null && tArmor.getItem() instanceof IElectricItem) {

			IElectricItem tArmorItem = (IElectricItem) tArmor.getItem();
			if (tArmorItem.canProvideEnergy(tArmor)) {

				IEnergyContainerItem chargable = (IEnergyContainerItem) aStack.getItem();
				int max = chargable.getMaxEnergyStored(aStack);
				int cur = chargable.getEnergyStored(aStack);
				if (cur < max) {

					int canUse = Math.max(max - cur, 0);
					if (canUse > this.getAEMaxPower(aStack)) canUse = (int) this.getAEMaxPower(aStack) - cur;

					double tCharge = ElectricItem.manager.discharge(tArmor, (canUse / 2D), Integer.MAX_VALUE, true, true, false);
					if (tCharge > 0)
						this.injectAEPower(aStack, tCharge * 2D);
				}
			}
		}
	}
}
