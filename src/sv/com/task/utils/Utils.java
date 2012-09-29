package sv.com.task.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class Utils {

	@PersistenceUnit
	protected EntityManagerFactory emf;
	protected EntityManager em;

	/**
	 * CnrAppsUtils proporciona métodos y propiedades utilitarias y de uso
	 * general para el framework de Aplicaciones de Centro Nacional de Registros
	 * CNRAPPS.
	 * <p>
	 * 
	 * Estas clases también pueden ser utilizadas por otras aplicaciones que se
	 * desarrollen para el Centro Nacional de Registros.
	 * <p>
	 * 
	 * @author Luis Ernesto Montecino Fuentes <luis.montecino@cnr.gob.sv>
	 * @version 1.0
	 * @since 08/06/2012
	 */
	public Utils() {

	}

	/**
	 * Convierte a un texto ecriptado en MD5.
	 * <p>
	 * La conversión de texto es necesaria para la validación de claves de
	 * usuario.
	 * <p>
	 * 
	 * @param texto
	 *            El texto que se desea convertir.
	 * @return El texto convertido en formato MD5.
	 * @since 1.0
	 */
	public static String getMD5String(String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(texto.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16).toUpperCase();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Devuelve un id único al azar.
	 * <p>
	 * El id único puede ser utilizado donde se requiera de un id único que no
	 * sea un incremental correlativo.
	 * <p>
	 * 
	 * @return El texto correspondiente a la id único.
	 * @since 1.0
	 */
	public static String getRandomUUID() {
		String strUUID;
		try {
			UUID genUUID = UUID.randomUUID();
			strUUID = genUUID.toString();
		} catch (Exception e) {
			return null;
		}
		return strUUID;
	}
}
