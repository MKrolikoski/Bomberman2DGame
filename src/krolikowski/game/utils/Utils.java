package krolikowski.game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Klasa pomocnicza
 */

public class Utils
{
	/**
	 * wczytuje plik tekstowy podany w œcie¿ce
	 * 
	 * @param path
	 *            œcie¿ka do pliku
	 * @return String z zawartoœci¹ pliku (linie pliku rozdzielone s¹ znakiem nowej
	 *         linii)
	 */
	public static String loadFileAsString(InputStream path)
	{
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(path, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");
			br.close();
		} catch (IOException e)
		{
			e.printStackTrace();
			try
			{
				if (br != null)
					br.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
		return builder.toString();
	}

	/**
	 * Zamienia przekazany String na int
	 * 
	 * @param number
	 *            string z numerem
	 * @return int
	 */
	public static int parseInt(String number)
	{
		try
		{
			return Integer.parseInt(number);
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Zwraca adres IPv4 komputera jeœli taki istnieje, w przeciwnym wypadku zwraca null
	 * @return adres IPv4
	 * @throws SocketException przy nieudanej próbie getNetworkInterfaces()
	 */
	public static InetAddress getLocalAddress() throws SocketException
	{
		Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
		while (ifaces.hasMoreElements())
		{
			NetworkInterface iface = ifaces.nextElement();
			Enumeration<InetAddress> addresses = iface.getInetAddresses();

			while (addresses.hasMoreElements())
			{
				InetAddress addr = addresses.nextElement();
				if (addr instanceof Inet4Address && !addr.isLoopbackAddress())
				{
					return addr;
				}
			}
		}

		return null;
	}

}
