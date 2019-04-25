package krolikowski.game.net.packet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import krolikowski.game.net.packet.Packet.PacketTypes;

public class PacketTest
{

	@Test
	public void testLookupPacketString()
	{
		PacketTypes pt = Packet.lookupPacket("02");
		PacketTypes result = PacketTypes.MOVE;
		assertEquals(result, pt);
	}

	@Test
	public void testLookupPacketInt()
	{
		PacketTypes pt = Packet.lookupPacket(02);
		PacketTypes result = PacketTypes.MOVE;
		assertEquals(result, pt);
	}

}
