/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jmobius.gameserver.network.serverpackets;

import java.util.List;

import com.l2jmobius.gameserver.instancemanager.FortManager;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.entity.Fort;

/**
 * @author KenM
 */
public class ExShowFortressInfo extends L2GameServerPacket
{
	public static final ExShowFortressInfo STATIC_PACKET = new ExShowFortressInfo();
	
	private ExShowFortressInfo()
	{
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x15);
		final List<Fort> forts = FortManager.getInstance().getForts();
		writeD(forts.size());
		for (Fort fort : forts)
		{
			final L2Clan clan = fort.getOwnerClan();
			writeD(fort.getResidenceId());
			writeS(clan != null ? clan.getName() : "");
			writeD(fort.getSiege().isInProgress() ? 0x01 : 0x00);
			// Time of possession
			writeD(fort.getOwnedTime());
		}
	}
}
