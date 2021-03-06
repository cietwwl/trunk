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
package com.l2jmobius.gameserver.network.clientpackets.friend;

import com.l2jmobius.gameserver.data.sql.impl.CharNameTable;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.clientpackets.L2GameClientPacket;
import com.l2jmobius.gameserver.network.serverpackets.friend.BlockListPacket;

public final class RequestUpdateBlockMemo extends L2GameClientPacket
{
	private static final String _C__D0_96_REQUESTUPDATEBLOCKMEMO = "[C] D0:96 RequestUpdateBlockMemo";
	
	private String cName;
	private String memo;
	
	@Override
	protected void readImpl()
	{
		cName = readS();
		memo = readS();
	}
	
	@Override
	protected void runImpl()
	{
		final L2PcInstance player = getClient().getActiveChar();
		player.getBlockList().setBlockMemo(CharNameTable.getInstance().getIdByName(cName), memo);
		player.sendPacket(new BlockListPacket(player));
	}
	
	@Override
	public String getType()
	{
		return _C__D0_96_REQUESTUPDATEBLOCKMEMO;
	}
	
}
